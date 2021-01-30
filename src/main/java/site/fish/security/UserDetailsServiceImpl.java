package site.fish.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish.entity.sys.User;
import site.fish.repository.sys.UserRepository;

/**
 * Description: [UserDetailsServiceImpl]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:22
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            logger.error(String.format("用户不存在：%s.", s));
            /*
             SpringSecurity默认的DaoAuthenticationProvider中，hideUserNotFoundExceptions=false
             如抛出UsernameNotFoundException，则错误信息将被隐藏，因此无需携带特定异常信息
             反之如想抛出相关信息，则抛出BadCredentialsException
             如：throw new BadCredentialsException(String.format("用户不存在 '%s'.", s))
            */
            throw new UsernameNotFoundException(null);
        } else {
            user.getAuthorities();
            return user;
        }
    }
}
