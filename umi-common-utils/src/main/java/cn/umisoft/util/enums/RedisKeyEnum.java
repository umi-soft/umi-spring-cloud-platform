package cn.umisoft.util.enums;

/**
 * @Description:
 * @Author: hujie@umisoft.cn
 * @Date: 2019/4/4 11:49 AM
 */
public enum RedisKeyEnum {
    USER_PERMISSIONS_KEY_PREFIX("USER_ROLE_"),
    PLATFORM_PERMISSIONS_KEY("PLATFORM_PERMISIONS_KEY");

    private String id;
    RedisKeyEnum(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.id;
    }
}
