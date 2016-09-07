package com.udaff.hostmonitor.ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CheckHost{
    private static final int REQUEST_COUNT = 10;
    private static final String COMMAND = "ping -n";
    private static final int REQUEST_TIMED_OUT = -1;

    public int ping(String host){

        Process process = null;
        BufferedReader reader = null;

        try { // did you know what does 'try' do and why you should to use it?
            process = Runtime.getRuntime().exec(COMMAND + " " + REQUEST_COUNT + " " + host);
            // it's would be better to use patterns instead of string concatenation.
            // "ping -n %d %s" - this is pattern for your case.
            // Please make an attempt to find out how to use it
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            ArrayList<Integer> resultArray = new ArrayList<>();
            // It's important to use interfaces instead of implementation when you declare a variable
            // It's newbies mistake that should be eliminated.
            String line = reader.readLine();

            while (line != null){
                if(parseLatencyFromString(line) != REQUEST_TIMED_OUT){
                    resultArray.add(parseLatencyFromString(line));
                }
                line = reader.readLine();
            }

            return avgLatency(resultArray);

        }catch (IOException e){
            e.printStackTrace();
            return REQUEST_TIMED_OUT;
            // you've wrote catch-block but for why?
            // which aim does it have? What do it do by design?
        }finally {
            if (process != null){
                process.destroy();
            }
            try {
                if (reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int parseLatencyFromString(String line){

           if (line.contains("time=") || line.contains("time<")){
               int startIndex = line.indexOf("time") + 5;
               int endIndex = line.indexOf("ms");
               String duration = line.substring(startIndex, endIndex).trim();

               return Integer.parseInt(duration);
            }else {
               return REQUEST_TIMED_OUT; // does it timeout in this case?
           }
    }

    private int avgLatency(ArrayList<Integer> resultArray){
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
