package com.zh.bootStart.util;

import org.springframework.stereotype.Component;
import java.util.*;

@Component("resultMap")
public class ResultMap<T> {//返回结果类
    private boolean status; //成功/失败标志
    private String message; //失败消息
    private Object object;  //存储查询的一条数据
    private List<T> list = new ArrayList<T>(); //存储查询的多少数据
    private long total;     //分页查询数据总条数

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
