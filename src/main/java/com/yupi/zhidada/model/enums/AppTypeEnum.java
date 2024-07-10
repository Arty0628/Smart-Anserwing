package com.yupi.zhidada.model.enums;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用类型枚举
 */
public enum AppTypeEnum {
    SCORE("得分类",0),
    TEST("测评类",1);
    private final String text;
    private final int value;
    AppTypeEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }
    public static AppTypeEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (AppTypeEnum anEnum : AppTypeEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * @return
     * 获取值列表
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }
    public int getValue() {
        return value;
    }
    public String getText() {
        return text;
    }
}