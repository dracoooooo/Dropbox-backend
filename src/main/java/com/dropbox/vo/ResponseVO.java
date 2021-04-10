package com.dropbox.vo;

import lombok.Data;

@Data
public class ResponseVO {

    private boolean isSuccess;

    private String msg;

    private Object data;

    public static ResponseVO Success(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = true;
        return responseVO;
    }

    public static ResponseVO Success(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = true;
        responseVO.msg = msg;
        return responseVO;
    }

    public static ResponseVO Success(Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = true;
        responseVO.data = data;
        return responseVO;
    }

    public static ResponseVO Success(String msg, Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = true;
        responseVO.msg = msg;
        responseVO.data = data;
        return responseVO;
    }

    public static ResponseVO Failure(){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = false;
        return responseVO;
    }

    public static ResponseVO Failure(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = false;
        responseVO.msg = msg;
        return responseVO;
    }

    public static ResponseVO Failure(Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = false;
        responseVO.data = data;
        return responseVO;
    }

    public static ResponseVO Failure(String msg, Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.isSuccess = false;
        responseVO.msg = msg;
        responseVO.data = data;
        return responseVO;
    }
}
