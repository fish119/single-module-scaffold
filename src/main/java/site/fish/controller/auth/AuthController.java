package site.fish.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import site.fish.security.Constant;
import site.fish.security.JwtTokenUtil;
import site.fish.service.auth.AuthService;
import site.fish.vo.auth.LoginVo;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "01.认证")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private AuthService authService;

    /**
     * Description: 用户名、密码：admin，前端传入md5后的密码，数据库中密码：
     * {bcrypt}$2a$10$Gm1noaQlGdiWoSIu8eI1Cea4iVeKC10OmdDtu9.d.lAZxUE9Yv0Ta
     *
     * @param loginVo : loginVo
     * @return : org.springframework.http.ResponseEntity<?>
     * @author : Morphling
     * @date : 2021/1/27 21:08
     */
    @ApiOperation("1.登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> login(@RequestBody @Valid LoginVo loginVo) {
        HashMap<String, String> map = new HashMap<>(2);
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(), loginVo.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenUtil.generateToken(userDetails);
        map.put("token", token);
        return ResponseEntity.ok(map);
    }

    @ApiOperation("2.刷新Token")
    @GetMapping("/api/refreshToken")
    public ResponseEntity<HashMap<String, String>> refreshToken(HttpServletRequest request) {
        String authHeader = request.getHeader(Constant.TOKEN_HEADER);
        HashMap<String, String> map = new HashMap<>(2);
        map.put("token", authService.refreshToken(authHeader));
        return ResponseEntity.ok(map);
    }
}
