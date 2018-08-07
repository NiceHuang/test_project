package cn.hnx.common.utils;

/**
 * Created by viruser on 2018/8/6.
 */
public class ResultMessageBuilder {
    public static ResultMessage build(){
        return new ResultMessage();
    }

    public static ResultMessage build(Object data){
        return new ResultMessage(data);
    }

    public static ResultMessage build(int status, String message){
        return new ResultMessage(status, message, null);
    }

    public static ResultMessage build(int status, String message, Object data){
        return new ResultMessage(status, message, data);
    }
}
