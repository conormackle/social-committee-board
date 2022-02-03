package com.aquaq.scb.response;

public class ResponseCodes {

    private ResponseCodes(){}

    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_FOUND_ERROR = 404;
    public static final int BAD_REQUEST_ERROR = 400;
    public static final int SERVICE_UNAVAILABLE_ERROR = 503;
    public static final int FORBIDDEN_ERROR = 403;
    public static final int UNAUTHORIZED_ERROR = 401;
    public static final int SUCCESS = 200;
}
