package com.model;

/**
 * @author:智霸霸
 * @Date 2019/8/28
 */
public class DataResult {
    private int status;//响应状态码
    private String msg;//响应信息
    private Object data;//响应数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object date) {
        this.data = date;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", date=" + data +
                '}';
    }
}
