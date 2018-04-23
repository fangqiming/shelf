package com.i000.stock.user.core.exception;

import com.i000.stock.user.core.constant.enums.ApplicationErrorMessage;
import com.i000.stock.user.core.exception.ServiceException;
import com.i000.stock.user.core.exception.WebApiException;
import com.i000.stock.user.core.result.Results;
import com.i000.stock.user.core.result.base.ResultEntity;
import com.i000.stock.user.core.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:32 2018/4/23
 * @Modified By:
 */
@Slf4j
@RestControllerAdvice
public class BaseController {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultEntity methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        return Results.newErrorResultEntity(ApplicationErrorMessage.INVALID_PARAMETER.getCode(), "方法参数转化异常");
    }

    @ExceptionHandler({ValidationUtils.ValidateException.class})
    public ResultEntity ValidateExceptionHandler(ValidationUtils.ValidateException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        return Results.newErrorResultEntity(e.getCode(), e.getMessage());
    }

    /**
     * web接口层异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(WebApiException.class)
    public ResultEntity webApiExceptionHandler(WebApiException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }

        return Results.newErrorResultEntity(e.getCode(), e.getMessage());
    }


    /**
     * 业务层异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResultEntity serviceExceptionHandler(ServiceException e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        return Results.newErrorResultEntity(e.getCode(), e.getMessage());
    }


    /**
     * 其它异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultEntity exceptionHandler(Exception e) {
        if (log.isErrorEnabled()) {
            log.error(e.getMessage(), e);
        }
        return Results.newErrorResultEntity(
                ApplicationErrorMessage.SERVER_ERROR.getCode(),
                ApplicationErrorMessage.SERVER_ERROR.getMsg()
        );
    }

}
