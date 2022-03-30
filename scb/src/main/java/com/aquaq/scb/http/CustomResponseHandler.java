package com.aquaq.scb.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CustomResponseHandler implements ResponseHandler<String>{

    @Override
    public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
        //Get the status of the response
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            if(entity == null) {
                return "";
            } else {
                return EntityUtils.toString(entity);
            }

        } else {
            return ""+status;
        }
    }

}
