package com.udaff.hostmonitor.ping;

import com.udaff.hostmonitor.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CheckHost{
    private static final int REQUEST_COUNT = 10;
    private static final int REQUEST_TIMED_OUT = -1;
    private static final String COMMAND = "ping -n %d %s";

    public int ping(String host) throws IOException {

        String command = String.format(COMMAND, REQUEST_COUNT, host);
        // did you know what does 'try' do and why you should to use it?
/*
             Udovychenko.P  - FIXED
             it's would be better to use patterns instead of string concatenation.
             "ping -n %d %s" - this is pattern for your case.
             Please make an attempt to find out how to use it
*/
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        List<Integer> resultArray = new ArrayList<>();
/*
             Udovychenko.P  - FIXED
             It's important to use interfaces instead of implementation when you declare a variable
             It's newbies mistake that should be eliminated.
*/
        String line = reader.readLine();

        while (line != null){
            if(parseLatencyFromString(line) != REQUEST_TIMED_OUT){
                resultArray.add(parseLatencyFromString(line));
            }
            line = reader.readLine();
        }

        reader.close();
        process.destroy();

        return avgLatency(resultArray);
    }

    private int parseLatencyFromString(String line){

           if (line.contains("time=") || line.contains("time<")){
               int startIndex = line.indexOf("time") + 5;
               int endIndex = line.indexOf("ms");
               String duration = line.substring(startIndex, endIndex).trim();

               return Integer.parseInt(duration);
            }else {
               return REQUEST_TIMED_OUT;
/*
                Udovychenko.P - No, but I didn't want to adding an extra constant..
                does it timeout in this case?
*/
           }
    }

    private int avgLatency(List<Integer> resultArray){
        int sum = 0;

        if (resultArray.isEmpty()){
            return REQUEST_TIMED_OUT;
        }
        for (Integer element : resultArray) {
            sum += element;
        }

        return sum / resultArray.size();
    }
}
