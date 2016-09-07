package com.udaff.hostmonitor;
/*
  - Udovychenko.P  - No, because "src" is marked as "Source root folder" in IDE
    and package name doesn't highlighting
 package name doesn't match your directories.
 You're should to see highlighting on your package name on your IDE
 in fact you're made package src.com.udaff.hostmonitor
*/


import com.udaff.hostmonitor.util.Util;

public class Main {

    public static void main(String[] args) {


        Util util = new Util();
        System.out.println(util.showResult(args[0], util.ping(args)));

        // try to use argument 'args' to pass url of target host.
        // and use 'default host' if you didn't pass any host

        // it's better to use some 'wrapper class' to return result from an 'util-method'.

        // In fact method ping can return a lot of useful information and you'll need it
        // in future.

        // Method 'showResult' can be overloaded and be able to operate with many result-wrappers
        // in one common way.

        // todo: please learn a bit about 'overrided' and 'overloaded' methods.
    }

}