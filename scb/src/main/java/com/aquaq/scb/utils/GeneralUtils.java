package com.aquaq.scb.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GeneralUtils {
    private static String appName;

    public GeneralUtils(@Value("${app_name}") String appName){
        GeneralUtils.appName = appName;
    }

    public static String getAppName(){ return appName; }

    public static long getCurrentTs(){
        return System.currentTimeMillis();
    }

}
