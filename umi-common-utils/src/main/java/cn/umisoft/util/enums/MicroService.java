package cn.umisoft.util.enums;

import cn.umisoft.util.constatns.MicroServiceConstatns;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum MicroService {

    SYSTEM_ADMIN(MicroServiceConstatns.SYSTEM_ADMIN, "系统管理");

    @Setter
    @Getter
    public String id;

    @Setter
    @Getter
    public String name;

    MicroService(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public static List<Map<String, String>> getAllDictionaries() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (MicroService item : MicroService.values()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", item.getId());
            map.put("value", item.getName());
            list.add(map);
        }
        return list;
    }
}
