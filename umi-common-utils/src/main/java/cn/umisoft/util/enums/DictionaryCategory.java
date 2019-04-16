package cn.umisoft.util.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DictionaryCategory {

    CATEGORY("1", "字典分类"),
    SINGLE("2", "单级字典"),
    TREE("3", "多级字典");

    @Getter
    @Setter
    private String key;
    @Getter
    @Setter
    private String value;
    DictionaryCategory(String key,String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.key;
    }

    public static List<Map<String, String>> getAllDictionaries() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (DictionaryCategory item : DictionaryCategory.values()) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", item.getKey());
            map.put("value", item.getValue());
            list.add(map);
        }
        return list;
    }
}
