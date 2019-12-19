package com.gobue.blink.common.utils.spring.web;

import com.jd.y.ipc.saas.common.spring.error.CommonErrorCode;
import com.jd.y.ipc.saas.common.utils.JsonUtils;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jd.y.ipc.saas.common.spring.error.Error;

@Controller
@RequestMapping("/error")
@NoArgsConstructor
public class AppErrorController implements ErrorController {

    private ErrorProperties errorProperties;

    public AppErrorController(ErrorProperties errorProperties) {
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

        HttpStatus status = getStatus(request);
        CommonErrorCode errorCode = CommonErrorCode.fromHttpStatus(status.value());
        Error error = new Error(errorCode.getCode(), request.getRequestURI(), status.getReasonPhrase());
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView(JsonUtils.OBJECT_MAPPER);
        view.setAttributesMap(JsonUtils.object2Map(error));
        mav.setView(view);
        return mav;

    }


    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        CommonErrorCode errorCode = CommonErrorCode.fromHttpStatus(status.value());
        Error error = new Error(errorCode.getCode(), request.getRequestURI(), status.getReasonPhrase());
        return new ResponseEntity<>(JsonUtils.object2Json(error), status);
    }

}

