package Test;

import java.net.InetAddress;

public class CheckHost {
    public static boolean ping(String addr){

        try {

            InetAddress address = InetAddress.getByName(addr);
            boolean isReach = address.isReachable(5000);
            return isReach;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}


