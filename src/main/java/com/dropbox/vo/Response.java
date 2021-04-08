package com.dropbox.vo;

import lombok.Data;

@Data
public class Response {

    private boolean isSuccess;

    private String msg;

    private Object data;

    public static Response Success(){
        Response response = new Response();
        response.isSuccess = true;
        return response;
    }

    public static Response Success(String msg){
        Response response = new Response();
        response.isSuccess = true;
        response.msg = msg;
        return response;
    }

    public static Response Success(Object data){
        Response response = new Response();
        response.isSuccess = true;
        response.data = data;
        return response;
    }

    public static Response Success(String msg, Object data){
        Response response = new Response();
        response.isSuccess = true;
        response.msg = msg;
        response.data = data;
        return response;
    }

    public static Response Failure(){
        Response response = new Response();
        response.isSuccess = false;
        return response;
    }

    public static Response Failure(String msg){
        Response response = new Response();
        response.isSuccess = false;
        response.msg = msg;
        return response;
    }

    public static Response Failure(Object data){
        Response response = new Response();
        response.isSuccess = false;
        response.data = data;
        return response;
    }

    public static Response Failure(String msg, Object data){
        Response response = new Response();
        response.isSuccess = false;
        response.msg = msg;
        response.data = data;
        return response;
    }
}
