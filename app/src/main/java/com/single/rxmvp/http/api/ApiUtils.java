package com.single.rxmvp.http.api;

import com.single.rxmvp.http.retrofit.RetrofitUtils;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *          接口工具类
 */

public class ApiUtils {

    private static PhoneApi phoneApi;
    private static UserApi userApi;

    public static PhoneApi getPhoneApi() {
        if (phoneApi == null) {
            phoneApi = RetrofitUtils.get().retrofit().create(PhoneApi.class);
        }
        return phoneApi;
    }

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = RetrofitUtils.get().retrofit().create(UserApi.class);
        }
        return userApi;
    }

}
