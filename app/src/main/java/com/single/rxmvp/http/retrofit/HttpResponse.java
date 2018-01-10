package com.single.rxmvp.http.retrofit;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *      http响应参数实体类
 *      通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 *      备注:这里与服务器约定返回格式
 */

public class HttpResponse {

    /**
     * 描述信息
     */
    @SerializedName("msg")
    private String msg;

    /**
     * 状态码
     */
    @SerializedName("retCode")
    private int code;

    /**
     * 数据对象（成功返回对象，失败返回错误说明）
     */
    @SerializedName("result")
    private Object result;


    /**
     * 是否成功 成功200
     * @return 成功 200
     */
    public boolean isSuccess(){
        return code == 200;
    }

    /**
     * 转换成字符串
     * @return 结果集
     */
    public String toString(){
        String response = "[http response]" + "{\"code\": " + code
                + ",\"msg\":" + msg + ",\"result\":"
                + new Gson().toJson(result) + "}";

        return response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
