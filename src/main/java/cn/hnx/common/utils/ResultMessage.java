package cn.hnx.common.utils;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by viruser on 2018/8/6.
 * http接口返回格式类
 */
public class ResultMessage {

    @JSONField(ordinal=1)
    private int status;
    @JSONField(ordinal=2)
    private String message;
    @JSONField(ordinal=3)
    private Object data;

    public ResultMessage() {
        this.status = ResponseMessage.SUCCESS.getCode();
        this.message = ResponseMessage.SUCCESS.getMessage();
    }

    public ResultMessage(Object data) {
        this.status = ResponseMessage.SUCCESS.getCode();
        this.message = ResponseMessage.SUCCESS.getMessage();
        this.data = data;
    }

    public ResultMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
