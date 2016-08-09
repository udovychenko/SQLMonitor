package Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Runner {

    public static void main(String[] args) {
        String host = "google.com";
        String command = "ping -n ";
        int requestCount = 10;
        String pingResult = "";

        StringBuffer output = new StringBuffer();

        Process p;
        try {

            p = Runtime.getRuntime().exec(command + requestCount + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int i = 0;
            ArrayList<Integer> result = new ArrayList<>();

            while((line = reader.readLine()) != null ){
                pingResult = line;

                if(line.indexOf("Request timed out") != -1){
                    result.add(0);
                }

                if (line.indexOf("time=") != -1 || line.indexOf("time<") != -1){
                    result.add(parsePingTime(pingResult));
                }
                i++;
            }
            reader.close();
            p.destroy();

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(date.format(new Date()) + " | " + host + " | " + arrayAvg(result));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int parsePingTime(String line){
        return Integer.parseInt(line.substring(line.indexOf("time") + 5, line.indexOf("ms")).trim());
    }

    public static int arrayAvg(ArrayList<Integer> array){
        int avg;
        int sum = 0;

        for (Integer element : array) {
            sum += element;
        }
        return sum/array.size();
    }
}
