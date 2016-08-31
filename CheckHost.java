import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CheckHost{
    private static final int REQUEST_COUNT = 10;
    private static final String COMMAND = "ping -n";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public int ping(String host) {

        Process process;

        try {
            process = Runtime.getRuntime().exec(COMMAND + " " + REQUEST_COUNT + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parsedLine;
            int i = 0;
            ArrayList<Integer> rollupResultArray = new ArrayList<>();

            while((parsedLine = reader.readLine()) != null ){

                if(parsedLine.contains("Request timed out")){
                    rollupResultArray.add(0);
                }

                if (parsedLine.contains("time=") || parsedLine.contains("time<")){
                    rollupResultArray.add(getLatency(parsedLine));
                }
                i++;
            }
            reader.close();
            process.destroy();

            return avg(rollupResultArray);

        }catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }

    public String printResult(String host){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | " + ping(host);
    }

    private int getLatency(String parsedLine){
        int startIndex = parsedLine.indexOf("time") + 5;
        int endIndex = parsedLine.indexOf("ms");

        String duration = parsedLine.substring(startIndex, endIndex).trim();

        return Integer.parseInt(duration);
    }

    private int avg(ArrayList<Integer> array){
        int sum = 0;

        if (array.isEmpty()){
            return -1;
        }
        for (Integer element : array) {
            sum += element;
        }

        return sum / array.size();
    }

}
