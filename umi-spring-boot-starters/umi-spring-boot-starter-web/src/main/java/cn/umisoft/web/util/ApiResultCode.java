package cn.umisoft.web.util;


/**
 * @description:
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 9:35 AM
 */
public enum ApiResultCode {
    /**
     * 未知错误
     */
    UNKNOWN("0"),
    /**
     * 正常请求返回结果
     */
    SUCCESS("1"),
    /**
     * 认证全局错误
     */
    AUTHENTICATION_GLOBAL_ERROR("2"),
    /**
     * 验证码过期
     */
    AUTHENTICATION_CAPTCHA_EXPIRE("2.1"),
    /**
     * 用户名或密码不正确
     */
    AUTHENTICATION_USERNAME_OR_PASSWORD_ERROR("2.2"),
    /**
     * 回话全局错误
     */
    SESSION_GLOBAL_ERROR("3"),
    /**
     * 非法回话，session不存在、token不存在等
     */
    SESSION_ILLEGAL("3.1"),
    /**
     * 回话过期，token过期了
     */
    SESSION_EXPIRED("3.2"),
    /**
     * 权限不足
     */
    PERMISSION_DENIED("4"),
    /**
     * Controller层发生错误，如数据校验、参数不完整等
     * Controller层若需要细分类型，可使用如"5.1"之类的value值
     */
    CONTROLLER_HANDLER_GLOBAL_ERROR("5"),
    /**
     * Spring-Web框架中异常
     */
    SPRING_WEB_EXCEPTION("5.1"),
    /**
     * 接口调用不符合规范，ServletRequestBindingException
     */
    SPRING_WEB_SERVLET_REQUEST_BINDING_EXCEPTION("5.1.1"),
    /**
     * 文件上传错误，MultipartException
     */
    SPRING_WEB_MULTIPART_EXCEPTION("5.1.2"),
    /**
     * 接口不存在，HttpRequestMethodNotSupportedException
     */
    SPRING_WEB_HTTP_REQUEST_METHOD_NOT_SUPPORT_EXCEPTION("5.1.3"),
    /**
     * 媒体类型错误，HttpMediaTypeException
     */
    SPRING_WEB_HTTP_MEDIA_TYPE_EXCEPTION("5.1.4"),
    /**
     * Session不存在，HttpSessionRequiredException
     */
    SPRING_WEB_HTTP_SESSION_REQUIRED_EXCEPTION("5.1.5"),
    /**
     * Request不完整，MissingServletRequestPartException
     */
    SPRING_WEB_MISSING_SERVLET_PART_EXCEPTION("5.1.6"),
    /**
     * ModelAndView定义错误，ModelAndViewDefiningException
     */
    SPRING_WEB_MODEL_AND_VIEW_DEFINING_EXCEPTION("5.1.7"),
    /**
     * 找不到适配器，NoHandlerFoundException
     */
    SPRING_WEB_NO_HANDLER_FOUND_EXCEPTION("5.1.8"),
    /**
     * 接口服务不可用，UnavailableException
     */
    SPRING_WEB_UNAVAIABLE_EXCEPTION("5.1.9"),
    /**
     * 参数不完整
     */
    ILLEGAL_ARGUMENT_EXCEPTION("5.2"),
    /**
     * Service层出现错误，如业务逻辑检查不合法等
     * Service层若需要细分类型，可使用如"6.1"之类的value值
     */
    SERVICE_HANDLER_GLOBAL_ERROR("6"),
    /**
     * Dao层处理错误，如SQL执行错误等
     * Dao层若需要细分类型，可使用如"7.1"之类的value值
     */
    DAO_HANDLER_GLOBAL_ERROR("7"),
    /**
     * redis命令执行失败
     */
    REDIS_COMMAND_EXECUTION_EXCEPTION("7.1");
//    RedisCommandExecutionException

    private String value;

    ApiResultCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
