package cn.umisoft.util.enums;

import cn.umisoft.util.constatns.MicroServiceConstatns;
import lombok.Getter;
import lombok.Setter;



public enum MicroServiceEnum {

    SYSTEM_ADMIN(MicroServiceConstatns.SYSTEM_ADMIN, "系统管理");

    @Setter
    @Getter
    public String id;

    @Setter
    @Getter
    public String name;

    MicroServiceEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
