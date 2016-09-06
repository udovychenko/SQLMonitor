package com.udaff.hostmonitor.ping;
//TEST
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

        try {
            process = Runtime.getRuntime().exec(COMMAND + " " + REQUEST_COUNT + " " + host);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            ArrayList<Integer> resultArray = new ArrayList<>();
            String line = reader.readLine();

            while (line != null){
                if(getLatencyFromString(line) != REQUEST_TIMED_OUT){
                    resultArray.add(getLatencyFromString(line));
                }
                line = reader.readLine();
            }

            return avgLatency(resultArray);

        }catch (IOException e){
            e.printStackTrace();
            return REQUEST_TIMED_OUT;
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

    private int getLatencyFromString(String line){

           if (line.contains("time=") || line.contains("time<")){
               int startIndex = line.indexOf("time") + 5;
               int endIndex = line.indexOf("ms");
               String duration = line.substring(startIndex, endIndex).trim();

               return Integer.parseInt(duration);
            }else {
               return REQUEST_TIMED_OUT;
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
