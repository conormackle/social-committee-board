package com.aquaq.scb.helper;

import com.aquaq.scb.response.ScbResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHelper {
    public static void testSuccessScbResponse(ScbResponse expectedResponse, ScbResponse actualResponse){
        assertEquals(expectedResponse.getResponse(), actualResponse.getResponse());
        assertEquals(expectedResponse.getResponseCode(), actualResponse.getResponseCode());
    }
}
