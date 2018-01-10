package com.single.rxmvp.http.exception;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          自定义服务器错误
 */

public class ServerException extends RuntimeException{

    private int code;
    private String msg;

    public ServerException(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

}
