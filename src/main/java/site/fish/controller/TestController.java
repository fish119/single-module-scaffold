package site.fish.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.fish.service.sys.UserService;
import site.fish.vo.sys.UserVo;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Description: [TestController]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 14:04
 */
@RestController
@Api(tags = "99.测试")
public class TestController {
    @Autowired
    private UserService userService;

    @ApiOperation("1.guest测试")
    @GetMapping("/test/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/test/testError")
    public ResponseEntity<?> testError(){
        HashMap<String, String> map = null;
        map.put("message","用户名或密码错误了啊");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("2.user测试")
    @GetMapping("/api/test")
    public ResponseEntity<String> userTest() {
        return ResponseEntity.ok("User Test");
    }

    @ApiOperation("3.admin测试")
    @GetMapping("/api/adminTest")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Admin Test");
    }

    @ApiOperation("4.注册测试")
    @PostMapping("/api/test/user")
    public ResponseEntity<UserVo> addUserTest(@RequestBody @Valid UserVo user){
        return ResponseEntity.ok(userService.addUser(user));
    }
}
