package com.udaff.hostmonitor;
// package name doesn't match your directories.
// You're should to see highlighting on your package name on your IDE
// in fact you're made package src.com.udaff.hostmonitor

import com.udaff.hostmonitor.ping.CheckHost;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {

        CheckHost c = new CheckHost();
        String host = "google.com";
        // try to use argument 'args' to pass url of target host.
        // and use 'default host' if you didn't pass any host
        int avgLatency = c.ping(host);
        // it's better to use some 'wrapper class' to return result from an 'util-method'.

        // In fact method ping can return a lot of useful information and you'll need it
        // in future.

        // Method 'printResult' can be overloaded and be able to operate with many result-wrappers
        // in one common way.

        // todo: please learn a bit about 'overrided' and 'overloaded' methods.

        System.out.println(printResult(host, avgLatency));
    }

    private static String printResult(String host, int avgLatency){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String outputString = currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | ";

        if (avgLatency >= 0) {
            return  outputString + avgLatency;
        }else {
            return outputString + "Request timed out";
        }
    }
}