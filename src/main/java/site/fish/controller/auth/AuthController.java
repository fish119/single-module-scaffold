package site.fish.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.fish.security.JwtTokenUtil;
import site.fish.vo.auth.LoginVo;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Description: [AuthController]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 20:07
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil tokenUtil;

    /**
     * Description: 用户名、密码：admin，前端传入md5后的密码，数据库中密码：
     * {bcrypt}$2a$10$Gm1noaQlGdiWoSIu8eI1Cea4iVeKC10OmdDtu9.d.lAZxUE9Yv0Ta
     * @author    : Morphling
     * @date      : 2021/1/27 21:08
     * @param loginVo : loginVo
     * @return    : org.springframework.http.ResponseEntity<?>
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Valid LoginVo loginVo) {
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails =  (UserDetails) authentication.getPrincipal();
            String token = tokenUtil.generateToken(userDetails);
            HashMap<String, String> map = new HashMap<>(2);
            map.put("token", token);
            return ResponseEntity.ok(map);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            throw new BadCredentialsException("用户名或密码错误");
        }
    }
}
