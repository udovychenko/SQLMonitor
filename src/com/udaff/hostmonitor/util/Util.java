package com.udaff.hostmonitor.util;

import com.udaff.hostmonitor.ping.CheckHost;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_HOST = "localhost";

    /*Udovychenko.P  - Methods of this class must be static or not?*/

    public String showResult(String host, int avgLatency){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String outputString = currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | ";

        if (avgLatency >= 0) {
            return  outputString + avgLatency;
        }else {
            return outputString + "Request timed out";
        }
    }

    private boolean checkPingArgs(String[] args) {
        return args.length <= 1;
    }

    public  int ping(String[] args){
        CheckHost c = new CheckHost();
        boolean isCorrect = checkPingArgs(args);
        String host;

        if (!isCorrect){
            throw new IllegalArgumentException("You must pass one or none arguments!");
        }

        if (args.length == 1) {
            host = args[0];
        }else {
            host = DEFAULT_HOST;
        }

        return c.ping(host);
    }

}
