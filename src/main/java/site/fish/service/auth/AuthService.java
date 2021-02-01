package site.fish.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import site.fish.repository.sys.UserRepository;
import site.fish.security.Constant;
import site.fish.security.JwtTokenUtil;

/**
 * Description: [AuthService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
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
            logger.error("401: 未登录用户请求刷新Token ||| Url=/api/refreshToken");
            throw new BadCredentialsException("认证失败，请登录后重试");
        }
        final String oldToken = authHeader.substring(Constant.TOKEN_PREFIX.length());
        String newToken = tokenUtil.refreshToken(oldToken);
        String username = tokenUtil.getUsernameFromToken(newToken);
        if (tokenUtil.canTokenBeRefreshed(oldToken, userRepository.findByUsername(username).getLastPasswordReset())) {
            return newToken;
        } else {
            logger.error("403: 认证过期，Token无法刷新 ||| Url=/api/refreshToken");
            throw new CredentialsExpiredException("认证过期，Token无法刷新，请重新登录");
        }
    }
}
