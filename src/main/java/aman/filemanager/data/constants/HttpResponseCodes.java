package aman.filemanager.data.constants;

public class HttpResponseCodes {
    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int GATEWAY_TIMEOUT = 504;
    public static final int PROCESSING = 102;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NO_CONTENT = 204; //No content but headers might be useful
    public static final int PARTIAL_CONTENT = 206;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORISED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int NOT_ACCEPTABLE = 406;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int PRECONDITION_FAILED = 412;


}
