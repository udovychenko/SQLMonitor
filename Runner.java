package Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Runner {

    public static void main(String[] args) {
        String host = "google.com";
        String command = "ping -n ";
        int requestCount = 10 ;

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command + requestCount + " " + host);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while((line = reader.readLine()) != null){
                output.append(line + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
