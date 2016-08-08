package Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Runner {

    public static void main(String[] args) {

        String ip = "google.com";
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String availability;

        availability = CheckHost.ping(ip) ? "reached" : "unreached";

        System.out.println(timeStamp + " | " + ip + " | " + availability);
    }

}
