package com.liuil.springboot.request;

import lombok.AccessLevel;
import lombok.Getter;

public enum Color {
    RED("mm"),

    YELLOW("nn"),

    BLUE("oo");

    @Getter(AccessLevel.PRIVATE)
    private String value;

    Color(String value) {
        this.value = value;
    }

    /**
     * 根据value查找枚举
     *
     * @param value
     * @return
     */
    public static Color findBy(String value) {
        if (value == null) {
            return null;
        }
        for (Color color : values()) {
            if (color.getValue().equals(value)) {
                return color;
            }
        }
        throw new IllegalArgumentException("value cannot be find");
    }


}
