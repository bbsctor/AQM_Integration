package com.example.gs.mvpdemo.base;

/**
 * Created by GaoSheng on 2016/10/21.
 * 抽取的一个基类的bean,直接在泛型中传data就行
 */

public class BaseHttpResult<T> {
    private int code;
    //private int status;
    private String msg;
    private T data;

    public void setPage(T page) {
        this.page = page;
    }

    private T page;

    //public int getStatus() {
        //return status;
    //}

    //public void setStatus(int status) {
        //this.status = status;
    //}

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        if(data != null)
            return data;
        else
            return page;
    }

    public void setData(T data) {
            this.data = data;
    }

    @Override
    public String toString() {
        return "BaseHttpResult{" +
                //"status=" + status +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
