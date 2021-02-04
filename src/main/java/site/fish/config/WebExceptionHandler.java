package site.fish.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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
public class WebExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public void unKnowExceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.error("500:" + ex.getClass().getName() + ex.getLocalizedMessage() + ex.getMessage() + "|||Url=" + request.getRequestURI());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.SERVER_ERROR);
    }

    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    public void authenticationExceptionHandler(Exception authException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("401:" + authException.getMessage() + "|||Url=" + request.getRequestURI());
        String msg = ObjectUtils.isEmpty(authException.getLocalizedMessage()) ? ExceptionMessage.BAD_CREDENTIALS : authException.getLocalizedMessage();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Full authentication is required to access this resource".equals(authException.getLocalizedMessage()) ?
                        ExceptionMessage.BAD_CREDENTIALS : msg);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void unauthorizedExceptionHandler(Exception accessDeniedException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("403:" + accessDeniedException.getLocalizedMessage() + "|||Url=" + request.getRequestURI());
        String msg = ObjectUtils.isEmpty(accessDeniedException.getLocalizedMessage()) ? ExceptionMessage.FORBIDDEN : accessDeniedException.getLocalizedMessage();
        response.sendError(HttpServletResponse.SC_FORBIDDEN, msg);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Map<String, Object> handleValidationExceptions(BindException ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("400:" + ex.getLocalizedMessage() + "|||Url=" + request.getRequestURI());
        Map<String, Object> result = new HashMap<>(6);
        Map<String, String> errors = new HashMap<>(2);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        result.put("timestamp", formatter.format(LocalDateTime.now()));
        result.put("status", HttpServletResponse.SC_BAD_REQUEST);
        result.put("message", ExceptionMessage.ARGUMENT_NOT_VALID);
        result.put("errors", errors);
        result.put("path", request.getRequestURI());
        return result;
    }
}
