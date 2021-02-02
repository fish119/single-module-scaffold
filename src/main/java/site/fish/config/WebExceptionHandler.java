package site.fish.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: [统一异常处理类]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/1 11:34
 */
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @ExceptionHandler(Exception.class)
//    public void unKnowExceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
//        logger.error("500:" + ex.getClass().getName() + ex.getLocalizedMessage() + ex.getMessage() + "|||Url=" + request.getRequestURI());
//        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
//    }
}
