package com.example.as.api.uitl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间
     * @return
     */
    public static String currentDate(){
        return simpleDateFormat.format(new Date());
    }
}
