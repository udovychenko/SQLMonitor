package com.udaff.hostmonitor.util;

import com.udaff.hostmonitor.Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final String DEFAULT_HOST = "localhost";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String showResult(String host, int avgLatency){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String outputString = currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | ";

        if (avgLatency >= 0) {
            return  outputString + avgLatency;
        }else {
            return outputString + "Request timed out";
        }
    }


}
