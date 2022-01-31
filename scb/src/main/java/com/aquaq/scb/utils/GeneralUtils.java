package com.aquaq.scb.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GeneralUtils {

    public static long getCurrentTs(){
        return System.currentTimeMillis();
    }

}
