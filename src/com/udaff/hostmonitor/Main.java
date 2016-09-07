package com.udaff.hostmonitor;
// package name doesn't match your directories.
// You're should to see highlighting on your package name on your IDE
// in fact you're made package src.com.udaff.hostmonitor

import com.udaff.hostmonitor.ping.CheckHost;
import com.udaff.hostmonitor.util.Util;

public class Main {
    private static final String DEFAULT_HOST = "localhost";

    public static void main(String[] args) {

        CheckHost c = new CheckHost();
        int avgLatency;

        if (!Util.checkPingArgs(args)){
            throw new IllegalArgumentException("You must pass one argument!");

        }else if(args.length == 1){
            avgLatency = c.ping(args[0]);
            System.out.println(Util.printResult(args[0], avgLatency));
        }else {
            avgLatency = c.ping(DEFAULT_HOST);
            System.out.println(Util.printResult(DEFAULT_HOST, avgLatency));
        }

        // try to use argument 'args' to pass url of target host.
        // and use 'default host' if you didn't pass any host

        // it's better to use some 'wrapper class' to return result from an 'util-method'.

        // In fact method ping can return a lot of useful information and you'll need it
        // in future.

        // Method 'printResult' can be overloaded and be able to operate with many result-wrappers
        // in one common way.

        // todo: please learn a bit about 'overrided' and 'overloaded' methods.
    }

}