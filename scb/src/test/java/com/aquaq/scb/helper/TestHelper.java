package com.aquaq.scb.helper;

import com.aquaq.scb.response.ResponseCodes;
import com.aquaq.scb.response.ScbResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestHelper {
    public static void testSuccessScbResponse(ScbResponse expectedResponse, ScbResponse actualResponse){
        assertEquals(expectedResponse.getResponse(), actualResponse.getResponse());
        assertEquals(expectedResponse.getResponseCode(), actualResponse.getResponseCode());
    }

    public static void testExceptionScbResponse(ScbResponse actualResponse){
        assertNotNull(actualResponse.getResponse());
        assertEquals(ResponseCodes.INTERNAL_SERVER_ERROR, actualResponse.getResponseCode());
    }
}
