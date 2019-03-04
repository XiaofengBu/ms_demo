package com.xiaofeng.ms.exception;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BindException.class,GlobalException.class})
    public Result<String> globalExceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        if(e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> exceptionList = bindException.getAllErrors();
            String msg = exceptionList.get(0).getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else if(e instanceof LoginException){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }else if (e instanceof MobileNotExistException){
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

    }
}
