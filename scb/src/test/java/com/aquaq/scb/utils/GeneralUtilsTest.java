package com.aquaq.scb.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneralUtilsTest {

    @Test
    public void test_getCurrentTs_returns_current_timestamp_in_millis() {
        assertEquals(GeneralUtils.getCurrentTs(), System.currentTimeMillis());
    }
}
