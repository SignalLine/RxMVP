package com.single.rxmvp.http.helper;

import com.google.gson.JsonElement;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *           数据解析helper
 */

public interface ParseHelper {

    /**
     * parse
     *
     * @param jsonElement
     * @return
     */
    Object[] parse(JsonElement jsonElement);

}
