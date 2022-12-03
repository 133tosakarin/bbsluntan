package com.dc.bbsluntan.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DengChao
 */
@Data
public class R <T>{
    private T data;
    private String msg;
    private Integer code;
    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R<>();
        r.data= msg;
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
