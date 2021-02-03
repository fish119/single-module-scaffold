package site.fish.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
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

    @ExceptionHandler(Exception.class)
    public void unKnowExceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
        logger.error("500:" + ex.getClass().getName() + ex.getLocalizedMessage() + ex.getMessage() + "|||Url=" + request.getRequestURI());
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常");
    }

    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    public void authenticationExceptionHandler(Exception authException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("401:" + authException.getMessage() + "|||Url=" + request.getRequestURI());
        String msg = ObjectUtils.isEmpty(authException.getLocalizedMessage()) ? ExceptionMessage.BAD_CREDENTIALS : authException.getLocalizedMessage();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Full authentication is required to access this resource".equals(authException.getLocalizedMessage()) ?
                        ExceptionMessage.BAD_CREDENTIALS : msg);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void unauthorizedExceptionHandler(Exception accessDeniedException, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("403:" + accessDeniedException.getLocalizedMessage() + "|||Url=" + request.getRequestURI());
        String msg = ObjectUtils.isEmpty(accessDeniedException.getLocalizedMessage()) ? ExceptionMessage.FORBIDDEN : accessDeniedException.getLocalizedMessage();
        response.sendError(HttpServletResponse.SC_FORBIDDEN, msg);
    }
}
