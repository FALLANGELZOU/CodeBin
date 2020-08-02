package com.angel.test.util.Http;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: Angel_zou
 * @Date: Created in 23:08 2020/8/1
 * @Connection: ahacgn@gmail.com
 * @Description: http工具类
 */
@Data
@Component
public class HttpResult {
    private int status;
    private String message;
    private Object data;

    public static HttpResult error(String message){
        return new HttpResult()
                .setStatus(400)
                .setMessage(message)
                .setData(null);
    }
    public static HttpResult error(String message,Object object){
        return new HttpResult()
                .setStatus(400)
                .setMessage(message)
                .setData(object);
    }

    public static HttpResult error(int status, String message){
        return new HttpResult()
                .setStatus(status)
                .setMessage(message)
                .setData(null);
    }

    public static HttpResult ok(Object data){
        return new HttpResult()
                .setStatus(200)
                .setMessage("success")
                .setData(data);
    }


    public HttpResult setData(Object data) {
        this.data = data;
        return this;
    }

    public HttpResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpResult setStatus(int status) {
        this.status = status;
        return this;
    }
}