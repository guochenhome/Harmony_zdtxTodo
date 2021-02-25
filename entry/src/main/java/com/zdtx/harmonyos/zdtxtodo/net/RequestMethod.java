package com.zdtx.harmonyos.zdtxtodo.net;

/**
 * 创建日期:2021/2/25·14:26
 * 功能说明:﹝HTTP请求类型﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public enum  RequestMethod {

    GET("GET"),

    POST("POST"),

    HEAD("HEAD"),

    OPTIONS("OPTIONS"),

    PUT("PUT"),

    DELETE("DELETE"),

    TRACE("TRACE")
            ;

    private String method;

    RequestMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
