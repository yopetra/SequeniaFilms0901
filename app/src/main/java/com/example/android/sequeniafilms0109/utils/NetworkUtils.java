package com.example.android.sequeniafilms0109.utils;

import java.io.IOException;

public class NetworkUtils {

    public static boolean isInternetAvailable(){
        final String command = "ping -c 1 google.com";
        try {
            return Runtime.getRuntime().exec(command).waitFor() == 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
