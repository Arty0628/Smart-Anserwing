package com.yupi.zhidada.model.enums;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ReviewStatusEnum {
    REVIEWING("待审核",0),
    PASS("通过",1),
    REJECT("拒绝",2);
    private final String text;
    private final int value;
    ReviewStatusEnum(String text,int value) {
        this.text = text;
        this.value = value;
    }
    public static ReviewStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (ReviewStatusEnum anEnum : ReviewStatusEnum.values()) {
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