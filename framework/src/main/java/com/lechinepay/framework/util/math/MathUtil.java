package com.lechinepay.framework.util.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

    public static String multiply100HalfUp2(Double data) {
        if (null == data) {
            return null;
        }
        return new BigDecimal(data).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toString();
    }

    public static String multiply100HalfUp(Double data) {
        if (null == data) {
            return null;
        }
        return new BigDecimal(data).multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_UP).toString();
    }

    public static String multiply100HalfUp(String data) {
        if (null == data) {
            return null;
        }
        return new BigDecimal(data).multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_UP).toString();
    }

    public static String divide100HalfUp2(String data) {
        if (null == data) {
            return null;
        }
        return new BigDecimal(data).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP).toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply100HalfUp("900.00"));
    }

}
