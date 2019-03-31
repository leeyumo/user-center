package com.leeyumo.userCenter.config;


import com.leeyumo.common.constants.BaseCodeMsg;
import com.leeyumo.common.exception.BusinessException;
import com.leeyumo.common.exception.SystemException;
import com.leeyumo.common.models.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public JsonResult handleBusinessException(BusinessException e){
        return JsonResult.res(e.getMsg(),e.getData());
    }

    @ExceptionHandler(SystemException.class)
    public JsonResult handleSystemException(SystemException e){
        logger.error("系统异常",e);
        return JsonResult.fail(BaseCodeMsg.SystemError);
    }

    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e){
        logger.error("系统异常",e);
        return JsonResult.fail(BaseCodeMsg.SystemError);
    }
}
