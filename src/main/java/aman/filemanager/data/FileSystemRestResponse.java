package aman.filemanager.data;

import aman.filemanager.data.constants.HttpResponseCodes;

import java.io.Serializable;

public class FileSystemRestResponse implements Serializable {
    public static transient final FileSystemRestResponse EXCEPTION_RESPONSE = new FileSystemRestResponse();
    public static transient final FileSystemRestResponse DUMMY_RESPONSE = new FileSystemRestResponse();


    static {
        EXCEPTION_RESPONSE.setResponseCode(HttpResponseCodes.INTERNAL_SERVER_ERROR);
        EXCEPTION_RESPONSE.setMessage("Exception Occurred!!");
        EXCEPTION_RESPONSE.setStatus(false);
        EXCEPTION_RESPONSE.setResult(null);

        DUMMY_RESPONSE.setResponseCode(HttpResponseCodes.SUCCESS);
        DUMMY_RESPONSE.setResult(null);
        DUMMY_RESPONSE.setStatus(true);
        DUMMY_RESPONSE.setMessage("Execution Successful");
    }

    private int responseCode;
    private String message;
    private Object result;
    private boolean status;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FileSystemRestResponse{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                ", result=" + result +
                ", status=" + status +
                '}';
    }
}
