package com.yupi.zhidada.model.enums;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用类型枚举
 */
public enum AppScoringStrategyEnum {
    CUSTOM("自定义",0),
    AI("AI",1);
    private final String text;
    private final int value;
    AppScoringStrategyEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }
    public static AppScoringStrategyEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (AppScoringStrategyEnum anEnum : AppScoringStrategyEnum.values()) {
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