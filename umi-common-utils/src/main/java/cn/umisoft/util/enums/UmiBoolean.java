package cn.umisoft.util.enums;

import lombok.Getter;
import lombok.Setter;

public enum UmiBoolean {
    TRUE("1"),
    FALSE("0");

    @Getter
    @Setter
    private String id;
    UmiBoolean(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
