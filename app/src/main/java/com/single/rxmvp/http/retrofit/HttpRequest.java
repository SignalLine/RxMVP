package com.single.rxmvp.http.retrofit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          构建Api请求参数
 */

public class HttpRequest {

    public static final String appKey = "1889b37351288";
    private static final String k_key = "key";

    /**
     * 获取基础request
     */
    public static final Map<String,Object> getRequest(){
        Map<String,Object> map = new HashMap<>();
        map.put(k_key,appKey);

        return map;
    }

}
