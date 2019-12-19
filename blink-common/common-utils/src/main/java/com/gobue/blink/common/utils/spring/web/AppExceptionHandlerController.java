package com.gobue.blink.common.utils.spring.web;

import com.google.common.base.Joiner;
import com.jd.y.ipc.saas.common.exception.AppBusinessException;
import com.jd.y.ipc.saas.common.spring.error.CommonErrorCode;
import com.jd.y.ipc.saas.common.spring.error.ErrorCode;
import com.jd.y.ipc.saas.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.jd.y.ipc.saas.common.spring.error.Error;
import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class AppExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Autowired
    protected MessageSource messageSource;

    protected Logger logger = LoggerFactory.getLogger(AppExceptionHandlerController.class);

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {

        ErrorCode errorCode = CommonErrorCode.BAD_REQUEST;
        List<ObjectError> allErrors = ex.getAllErrors();
        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, errorCode.getMessage());
        return createResponseEntity(errorCode, request.getDescription(false), errorMessage);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ErrorCode errorCode = CommonErrorCode.BAD_REQUEST;
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String errorMessage = extractErrorMessageFromObjectErrors(allErrors, errorCode.getMessage());
        return createResponseEntity(errorCode, request.getDescription(false),
                messageSource.getMessage(errorMessage, null, LocaleContextHolder.getLocale()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {

        logger.error("spring web exception: " + ex.getMessage(), ex);
        ErrorCode errorCode = CommonErrorCode.fromHttpStatus(status.value());
        return createResponseEntity(errorCode, request.getDescription(false), errorCode.getMessage());
    }




    //业务异常
    @ExceptionHandler(value = AppBusinessException.class)
    public ResponseEntity<Object> handleAppBusinessException(HttpServletRequest request, AppBusinessException e) {
        return createResponseEntity(e.getCode(), e.getHttpStatus(), request.getRequestURI(),
                messageSource.getMessage(e.getMessage(), e.getArgs(), LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception e) {

        logger.error("internal error: " + e.getMessage(), e);
        ErrorCode errorCode = CommonErrorCode.INTERNAL_ERROR;
        return createResponseEntity(errorCode, request.getRequestURI(), errorCode.getMessage());

    }


    private ResponseEntity<Object> createResponseEntity(ErrorCode errorCode, String requestUri, String message) {
        return createResponseEntity(errorCode.getCode(), errorCode.getStatus(), requestUri, message);
    }

    private ResponseEntity<Object> createResponseEntity(String code, int httpStatus, String requestUri, String message) {
        Error error = new Error(code, requestUri, message);
        String json = JsonUtils.object2Json(error);

        return ResponseEntity.status(HttpStatus.valueOf(httpStatus)).body(json);
    }


    private String extractErrorMessageFromObjectErrors(List<ObjectError> allErrors, String defaultMessage) {
        if(allErrors == null || allErrors.isEmpty()) {
            return defaultMessage;
        } else {
            List<String> errorMessages = allErrors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return Joiner.on(",").skipNulls().join(errorMessages);
        }
    }
}
