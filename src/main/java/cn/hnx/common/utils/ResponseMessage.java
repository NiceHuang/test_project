package cn.hnx.common.utils;

/**
 * Created by viruser on 2018/8/6.
 *  http接口返回信息
 */
public enum ResponseMessage {
    SUCCESS(0, "success"),
    INTERNAL_ERROR(-1, "系统内部错误"),
    SERVER_ERROR(-2, "未知异常"),
    PARAM_NEED_ERROR(-101, "请求参数缺失"),
    PARAM_FORMAT_ERROR(-102, "请求参数格式错误"),
    PERMISSION_DENIED(-100, "没有权限"),
    INVALID_PASSWORD(-102, "用户名或密码错误"),
    INVALID_TOKEN(-104, "token无效");

    private int code;
    private String message;

    ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
