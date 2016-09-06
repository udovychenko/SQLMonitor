package com.udaff.hostmonitor;

import com.udaff.hostmonitor.ping.CheckHost;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {

        CheckHost c = new CheckHost();
        String host = "google.com";
        int avgLatency = c.ping(host);

        System.out.println(printResult(host,avgLatency));
    }

    public static String printResult(String host, int avgLatency){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String outputString = currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | ";

        if (avgLatency >= 0) {
            return  outputString + avgLatency;
        }else {
            return outputString + "Request timed out";
        }
    }

}