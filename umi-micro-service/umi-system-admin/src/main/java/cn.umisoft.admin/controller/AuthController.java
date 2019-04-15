package cn.umisoft.admin.controller;

import cn.umisoft.admin.service.ITMenuService;
import cn.umisoft.admin.service.ITRoleService;
import cn.umisoft.admin.entity.TUser;
import cn.umisoft.admin.service.ITSecurityService;
import cn.umisoft.admin.service.ITUserService;
import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.enums.RedisKeyEnum;
import cn.umisoft.util.jwt.JwtUtils;
import cn.umisoft.util.jwt.JwtProperties;
import cn.umisoft.util.api.ApiResult;
import cn.umisoft.util.api.ApiResultWrapper;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// TODO 将name属性同步给umi-spring-boot-admin工程
// TODO 将系统内置API自动入库功能迁移给umi-spring-boot-admin工程
/**
 * @description: <p>认证相关控制器</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/2/19 10:37 PM
 */
@RestController
@RequestMapping(value = "/admin/auth", name = "登录与权限加载控制器")
public class AuthController {

    @Autowired
    protected JwtProperties properties;

    @Autowired
    protected ITSecurityService securityService;
    @Autowired
    protected ITUserService userService;
    @Autowired
    protected ITRoleService roleService;
    @Autowired
    protected ITMenuService menuService;
    @Autowired
    protected StringRedisTemplate redisTemplate;
    @Autowired
    protected Producer producer;

    /**
     * @description: <p>base64图片验证码，后端添加redis服务，支持key值前端指定，便于分布式环境下session id不一致问题</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/22 10:22 AM
     * @param: 可指定key值
     * @return:
     */
    @GetMapping(value = "captcha", name = "生成验证码（无安全限制）")
    public ApiResult captcha(HttpServletRequest request, String key) {
        if (key == null || "".equals(key.trim())) {
            key = request.getSession(true).getId();
        }
        String text = producer.createText();
        redisTemplate.opsForValue().set(key, text, 60, TimeUnit.SECONDS);
        BufferedImage bi = producer.createImage(text);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpeg", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResultWrapper.success("data:image/jpeg;base64," + Base64.encodeBase64String(os.toByteArray()));
    }
    /**
     * @description: <p>登录，验证码支持携带自定义的key值，便于分布式环境使用</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:15 PM
     */
    @PostMapping(value = "login", name = "用户名+密码+验证码登录")
    public ApiResult login(HttpServletRequest request, String loginName, String password, String captcha, String key){
        Assert.notNull(captcha, "验证码不能为空");
        Assert.notNull(loginName, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        if (key == null || "".equals(key.trim())) {
            key = request.getSession(true).getId();
        }
        String redisCaptcha = redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        JSONObject result = new JSONObject();
        if (!captcha.equals(redisCaptcha)) {
            // 验证码错误
            result.put("result", "2");
        } else {
            TUser user = userService.login(loginName, password);
            if (user == null) {
                result.put("result", "3");
            } else {
                // 生成网关层对外使用的token
                String jwt = JwtUtils.create((JSONObject)JSONObject.toJSON(user), properties.getGatewaySecret(), properties.getMinutes(), JwtUtils.GATEWAY_REDIS_USER_TOKEN_KEY_PREFIX, redisTemplate);
                result.put("result", "1");
                result.put("token", jwt);
            }
        }
        return ApiResultWrapper.success(result);
    }
    /**
     * @description: <p>获取用户信息和用户的所有可用角色id列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:14 PM
     */
    @GetMapping(value = "user-authorities", name = "获取用户信息和用户角色ID列表")
    public ApiResult userAuthorities(){
        JSONObject result = new JSONObject();
        String id = UmiUserContextHolder.getContext();

        result.put("user", userService.getById(id));
        result.put("roles", roleService.findAllByCurrentUserId());

        return ApiResultWrapper.success(result);
    }
    /**
     * @description: <p>获取所有前端路由和角色的映射关系</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:14 PM
     */
    @GetMapping(value = "system-authorities", name = "获取系统中所有的前端路由与角色映射关系")
    public ApiResult systemAuthorities(){
        return ApiResultWrapper.success(this.menuService.findAllRouterRoles());
    }
    /**
     * @description: <p>返回所有服务的安全资源与角色id的映射关系</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:14 PM
     */
    @GetMapping(value = "all-platform-permissions", name = "将所有服务API级别权限控制信息加载至redis")
    public ApiResult securityRoleMapping(){
        Map<String, Set<String>> map = this.securityService.findAllSecurityRoleMapping();
        redisTemplate.opsForValue().set(RedisKeyEnum.PLATFORM_PERMISSIONS_KEY.toString(), JSONObject.toJSONString(map));
        return ApiResultWrapper.success();
    }
    /**
     * @description: <p>获取用户信息和用户的所有可用角色id列表</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/2 2:14 PM
     */
    @GetMapping(value = "user-permissions", name = "将当前用户的权限信息加载至redis")
    public ApiResult userPermissions(){
        Set<String> roles = roleService.findAllByCurrentUserId();
        redisTemplate.opsForValue().set(RedisKeyEnum.USER_PERMISSIONS_KEY_PREFIX.toString() + UmiUserContextHolder.getContext(), JSONObject.toJSONString(roles));
        return ApiResultWrapper.success();
    }
    /**
     * @description: <p>退出登录，cn.umisoft.admin.util.interceptor.JWTInterceptor 拦截器必须拦截token，确保分布式环境下，能够正常退出</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/22 8:12 AM
     */
    @GetMapping(value = "logout", name = "退出登录")
    public ApiResult logout(){
        JwtUtils.logout(redisTemplate);
        return ApiResultWrapper.success("退出登录成功");
    }
}