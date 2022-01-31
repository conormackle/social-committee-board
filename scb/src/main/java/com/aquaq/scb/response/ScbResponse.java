package com.aquaq.scb.response;

import com.aquaq.scb.utils.GeneralUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@ToString
@Log4j2
/* Standard API response for all controller calls */
public class ScbResponse {
    private int responseCode;
    private Object response;
    private long timeReceived;
    private long timeReturned;

    public ScbResponse() {
        timeReceived = GeneralUtils.getCurrentTs();
        responseCode = ResponseCodes.SUCCESS;
    }

    public static ScbResponse createSuccessResponse(Object responseObject){
        ScbResponse response = new ScbResponse();
        response.setResponse(responseObject);
        response.setTimeReturned(System.currentTimeMillis());
        log.debug("response = " + response);

        return response;
    }

    public static ScbResponse createFailedResponse(Exception exception){
        ScbResponse response = new ScbResponse();
        response.setResponseCode(ResponseCodes.INTERNAL_SERVER_ERROR);
        response.setResponse(exception);
        log.error(exception);
        return response;
    }
}
