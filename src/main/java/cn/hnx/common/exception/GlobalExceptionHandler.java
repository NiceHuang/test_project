package cn.hnx.common.exception;

import cn.hnx.common.utils.ResponseMessage;
import cn.hnx.common.utils.ResultMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


/**
 * Created by viruser on 2018/8/7.
 */

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultMessage defaultErrorHandler(Exception e){

        ResultMessage message = new ResultMessage();
        message.setStatus(ResponseMessage.SERVER_ERROR.getCode());
        message.setMessage(e.getMessage());
        message.setData(new HashMap<>());
        return message;
    }
}
