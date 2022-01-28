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
        responseCode = 1;
    }

    public static ScbResponse createResponse(Object responseObject){
        ScbResponse response = new ScbResponse();
        response.setResponse(responseObject);
        response.setTimeReturned(System.currentTimeMillis());
        log.debug("response = " + response);

        return response;
    }
}
