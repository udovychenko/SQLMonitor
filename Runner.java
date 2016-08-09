package Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {

    public static void main(String[] args) {
        String host = "blade-14";
        String command = "ping -l 40000 -n ";
        int requestCount = 4;
        String pingResult = "";
        int[] result = new int[requestCount+7];
        int avgTime;

        StringBuffer output = new StringBuffer();

        Process p;
        try {

            p = Runtime.getRuntime().exec(command + requestCount + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int i = 0;

            while((line = reader.readLine()) != null ){
                pingResult = line;
                result[i] = parsePingTime(pingResult);
                System.out.println(result[i]);
                i++;
                //output.append(line + "\n");
            }
            reader.close();
            p.destroy();

            int sum = 0;
            for (int j = 0; j < result.length; j++) {
                sum = sum + result[j];
            }
            avgTime = sum / (result.length-7);
            System.out.println("avgTime = " + avgTime);

        }catch (Exception e){
            e.printStackTrace();
        }

        //System.out.println(pingResult);
    }

    public static int parsePingTime(String line){
        try{
            return Integer.parseInt(line.substring(line.indexOf("time") + 5, line.indexOf("ms")).trim());
        }catch (Exception e) {
            return 0;
        }
    }


}
