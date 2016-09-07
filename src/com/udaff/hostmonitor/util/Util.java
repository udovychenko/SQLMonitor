package com.udaff.hostmonitor.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String printResult(String host, int avgLatency){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String outputString = currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | ";

        if (avgLatency >= 0) {
            return  outputString + avgLatency;
        }else {
            return outputString + "Request timed out";
        }
    }

    public static boolean checkPingArgs(String[] args) {
        if (args.length > 1){
            return false;
        }
        return true;
    }

    public static void runPing(){

    }

}
