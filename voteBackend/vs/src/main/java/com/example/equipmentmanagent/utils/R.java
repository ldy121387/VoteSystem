package com.example.equipmentmanagent.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     */
    private String code;

    /**
     * 编码描述
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    public static <T> R<T> success(T data) {
        R r = new R();
        r.setCode(String.valueOf(200));
        r.setMsg("success");
        r.setData(data);
        return r;
    }

    public static <T> R<T> ok(String msg) {
        R r = new R();
        r.setCode(String.valueOf(200));
        r.setMsg(msg);
        return r;
    }

    public static<T> R<T> failed(String msg) {
        R r = new R();
        r.setMsg(msg);
        r.setCode(String.valueOf(500));
        return r;
    }
}
