package cn.umisoft.util.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Gender {
    BOY("1", "男"),
    GIRL("0", "女");

    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private String value;
    Gender(String key,String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key;
    }

    public static List<Map<String, String>> getAllDictionaries() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Gender item : Gender.values()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", item.getKey());
            map.put("value", item.getValue());
            list.add(map);
        }
        return list;
    }
}
