package com.udaff.hostmonitor;
/*
    Udovychenko.P  - No, because "src" is marked as "Source root folder" in IDE
    and package name doesn't highlighting
 package name doesn't match your directories.
 You're should to see highlighting on your package name on your IDE
 in fact you're made package src.com.udaff.hostmonitor
*/

import com.udaff.hostmonitor.ping.CheckHost;
import com.udaff.hostmonitor.util.Util;

public class Main {

    public static void main(String[] args) throws Exception {

        CheckHost c = new CheckHost();
        String host;
        int avgLatency;

        if (args.length == 0){
            host = Util.DEFAULT_HOST;
        }else{
            host = args[0];
        }

        avgLatency = c.ping(host);

        System.out.println(Util.showResult(host, avgLatency));
/*
        Udovychenko.P  - horrible decision, but it's working!
         try to use argument 'args' to pass url of target host.
         and use 'default host' if you didn't pass any host
*/
        // it's better to use some 'wrapper class' to return result from an 'util-method'.

        // In fact method ping can return a lot of useful information and you'll need it
        // in future.

        // Method 'showResult' can be overloaded and be able to operate with many result-wrappers
        // in one common way.

        // todo: please learn a bit about 'overrided' and 'overloaded' methods.
    }
}