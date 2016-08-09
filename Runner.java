import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Runner {

    private static final int REQUEST_COUNT = 10;
    private static final String COMMAND = "ping -n ";

    public static void main(String[] args) {

        String host = "google.com";

        printResult(host, ping(host));
    }

    private static int ping(String host) {
        Process process;

        try {

            process = Runtime.getRuntime().exec(COMMAND + REQUEST_COUNT + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parsedLine;
            int i = 0;
            ArrayList<Integer> rollupResultArray = new ArrayList<>();

            while((parsedLine = reader.readLine()) != null ){

                if(parsedLine.contains("Request timed out")){
                    rollupResultArray.add(0);
                }

                if (parsedLine.contains("time=") || parsedLine.contains("time<")){
                    rollupResultArray.add(getResponseTime(parsedLine));
                }
                i++;
            }
            reader.close();
            process.destroy();

            return avg(rollupResultArray);

        }catch (Exception e){

            e.printStackTrace();
            return -1;

        }
    }

    private static void printResult(String host, int avgResponse){

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormat.format(new Date()) + " | " + host + " | " + avgResponse);

    }

    private static int getResponseTime(String parsedLine){
        return Integer.parseInt(parsedLine.substring(parsedLine.indexOf("time") + 5, parsedLine.indexOf("ms")).trim());
    }

    private static int avg(ArrayList<Integer> array){
        int sum = 0;

        for (Integer element : array) {
            sum += element;
        }
        return array.size() == 0 ? -1 : sum/array.size();
    }
}
