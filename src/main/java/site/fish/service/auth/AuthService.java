package site.fish.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import site.fish.config.ExceptionMessage;
import site.fish.repository.sys.UserRepository;
import site.fish.security.Constant;
import site.fish.security.JwtTokenUtil;

/**
 * Description: [AuthService]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/31 13:02
 */
@Service
public class AuthService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private UserRepository userRepository;

    public String refreshToken(String authHeader) {
        if (ObjectUtils.isEmpty(authHeader)) {
            logger.error("401: "+ExceptionMessage.REFRESH_TOKEN_BY_GUEST+" ||| Url=/api/refreshToken");
            throw new BadCredentialsException(ExceptionMessage.BAD_CREDENTIALS);
        }
        final String oldToken = authHeader.substring(Constant.TOKEN_PREFIX.length());
        String newToken = tokenUtil.refreshToken(oldToken);
        String username = tokenUtil.getUsernameFromToken(newToken);
        if (tokenUtil.canTokenBeRefreshed(oldToken, userRepository.findByUsername(username).getLastPasswordReset())) {
            return newToken;
        } else {
            logger.error("403: " + ExceptionMessage.TOKEN_EXPIRED + " ||| Url=/api/refreshToken");
            throw new CredentialsExpiredException(ExceptionMessage.TOKEN_EXPIRED);
        }
    }
}
