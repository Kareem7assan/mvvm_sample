package ir.orangehat.movieinfo.bussines.networking.api;

import com.google.gson.JsonElement;

import static ir.orangehat.movieinfo.bussines.networking.api.Status.ERROR_SERVER;
import static ir.orangehat.movieinfo.bussines.networking.api.Status.LOADING;
import static ir.orangehat.movieinfo.bussines.networking.api.Status.SUCCESS;

public class ApiStates {
    private final Status status;
    private final Throwable error;
    private final String error_msg;

    private ApiStates(Status status, Throwable error,String error_msg) {
        this.status = status;
        this.error = error;
        this.error_msg=error_msg;
    }


    public static ApiStates loading() {
        return new ApiStates(LOADING, null,null);
    }

    public static ApiStates success() {
        return new ApiStates(SUCCESS, null,null);
    }

    public static ApiStates error_server( Throwable error,String error_msg) {
        return new ApiStates(ERROR_SERVER, error,error_msg);
    }
    public static ApiStates error_msg( String error) {
        return new ApiStates(ERROR_SERVER, null,error);
    }


    public Status getStatus() {
        return status;
    }

    public Throwable getError() {
        return error;
    }

    public String getError_msg() {
        return error_msg;
    }
}
