package com.example.util;

/**
 * @Authot: Albert Akimov
 * @Date: 26.09.2021
 * @Description:
 */

public class DatePickerUtil {

    public static String buildDateString(int i, int i1, int i2) {

        String year     = String.valueOf(i);
        String mount    = String.valueOf(i1);
        String day      = String.valueOf(i2);

        if(i1 + 1 <= 10)
            mount = "0" + (i1 + 1);

        if(i2 < 10)
            day = "0" + i2;

        return year + mount + day;
    }
}
