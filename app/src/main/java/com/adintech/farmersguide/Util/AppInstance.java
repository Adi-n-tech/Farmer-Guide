package com.adintech.farmersguide.Util;

public class AppInstance {

    private static AppInstance appInstance = new AppInstance();

    public static AppInstance getAppInstance() {
        return appInstance;
    }

}
