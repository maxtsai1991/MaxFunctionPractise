package com.example.demo.jsonpersoncat.util;

import java.text.DecimalFormat;
import java.util.Objects;

public class TextUtil {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");
    public static String convertDecimalString(String value){
        return convertDecimalString(Double.parseDouble(value));
    }
    public static String convertDecimalString(double value){
        return decimalFormat.format(value);
    }

    public static String reverseDecimalFormat(String decimalString){
        try {
            int value = Objects.requireNonNull(new DecimalFormat().parse(decimalString)).intValue();
            return String.valueOf(value);
        }catch (Exception e){
            return decimalString;
        }
    }
}
