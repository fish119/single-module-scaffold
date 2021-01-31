package site.fish.service;

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
    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private UserRepository userRepository;

    public String refreshToken(String authHeader) {
        if (ObjectUtils.isEmpty(authHeader)) {
            throw new BadCredentialsException("认证失败，请登录后重试");
        }
        final String oldToken = authHeader.substring(Constant.TOKEN_PREFIX.length());
        String newToken = tokenUtil.refreshToken(oldToken);
        String username = tokenUtil.getUsernameFromToken(newToken);
        if (tokenUtil.canTokenBeRefreshed(oldToken, userRepository.findByUsername(username).getLastPasswordReset())) {
            return newToken;
        } else {
            throw new CredentialsExpiredException("认证过期，Token无法刷新，请重新登录");
        }
    }
}
