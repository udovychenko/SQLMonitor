import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class CheckHost{
    private static final int REQUEST_COUNT = 10;
    private static final String COMMAND = "ping -n";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String REQUEST_TIMED_OUT = "Request timed out";

    public int ping(String host) {


        try {
            Process process;
            process = Runtime.getRuntime().exec(COMMAND + " " + REQUEST_COUNT + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parsedLine;

            ArrayList<Integer> rollupResultArray = new ArrayList<>();

            while((parsedLine = reader.readLine()) != null ){

                if(parsedLine.contains(REQUEST_TIMED_OUT)){ // it's will be better if you put all string literal to constant (code style)
                    rollupResultArray.add(0); // timeout it's not a zero!
                }

                // both clauses can be united because now they are do some logic that related with method getLatency(..)
                if (parsedLine.contains("time=") || parsedLine.contains("time<")){
                    rollupResultArray.add(getLatency(parsedLine));
                }
            }

            return avg(rollupResultArray);

        }catch (IOException e){
            e.printStackTrace();
            return -1;
        }finally {
            if (reader != null);{
                reader.close();
            }

            process.destroy();
        }

    }

    public String printResult(String host){  // unused method
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + " | " + host + " | " + ping(host);
    }

    // unsafe method. because you are able to pass any string to it.
    private int getLatency(String parsedLine){
        int startIndex = parsedLine.indexOf("time") + 5;
        int endIndex = parsedLine.indexOf("ms");

        String duration = parsedLine.substring(startIndex, endIndex).trim();

        return Integer.parseInt(duration);
    }

    // bad argument name
    private int avg(ArrayList<Integer> array){
        int sum = 0;

        if (array.isEmpty()){
            return -1; // is it correct value to show? you cam declare some constant, return it here and replace it with some string in the method "showResult()"
        }
        for (Integer element : array) {
            sum += element;
        }

        // instead of your own implementation of avg() you can use it from standard library java.lang.
        return sum / array.size();
    }
}
