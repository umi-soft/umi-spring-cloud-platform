package cn.umisoft.util.context;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @description: <p>将当前用户USER_ID以ThreadLocal变量保存</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/27 11:20 AM
 */
@Slf4j
public class UmiUserContextHolder {

    public static String USER_ID_KEY = "id";
    public static String JWT_KEY = "jwt";

    private static ThreadLocal<Map<String, String>> context = new ThreadLocal<Map<String, String>>() {
        @Override
        protected Map<String, String> initialValue() {
            // TODO Auto-generated method stub
            throw new RuntimeException("缺失用户信息");
        }
        @Override
        public Map<String, String> get() {
            // TODO Auto-generated method stub
            return super.get();
        }
        @Override
        public void set(Map<String, String> context) {
            // TODO Auto-generated method stub
            super.set(context);
        }
        @Override
        public void remove() {
            // TODO Auto-generated method stub
            super.remove();
        }
    };

    public static void setContext(Map<String, String> map) {
        context.set(map);
    }

    public static String getContext() {
        if (context.get() == null) {
            log.info("UserContextHolder中没有上下文信息");
            return null;
        }
        return context.get().get(USER_ID_KEY);
    }

    public static String getJwtToken() {
        if (context.get() == null) {
            log.info("UserContextHolder中没有上下文信息");
            return null;
        }
        return context.get().get(JWT_KEY);
    }


}
