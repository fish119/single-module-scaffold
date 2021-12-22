package site.fish.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish.service.sys.UserService;
import site.fish.vo.sys.UserVo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description: [TestController]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 14:04
 */
@RestController
@Api(tags = "99.测试")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation("1.guest测试")
    @GetMapping("/test/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @ApiOperation("99.List类型参数测试")
    @PostMapping("/test/queryTimeAnalysis")
    public ResponseEntity<?> queryTimeAnalysis(@RequestParam ArrayList<Integer> ids) {
        if (ids == null || ids.size() == 0) {
            return ResponseEntity.ok("参数错误");
        }
        logger.error("IDS:"+ids);
        System.out.println(ids);
        return ResponseEntity.ok(ids);
    }

    @ApiOperation(value = "2.异常测试")
    @GetMapping("/test/testError")
    public ResponseEntity<?> testError(){
        HashMap<String, String> map = null;
        map.put("message","用户名或密码错误了啊");
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiOperation("3.user测试")
    @GetMapping("/api/test")
    public ResponseEntity<String> userTest() {
        return ResponseEntity.ok("User Test");
    }

    @ApiOperation("4.admin测试")
    @GetMapping("/api/adminTest")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Admin Test");
    }

    @ApiOperation("5.注册测试")
    @PostMapping("/api/test/user")
    public ResponseEntity<UserVo> addUserTest(@RequestBody @Valid UserVo user){
        return ResponseEntity.ok(userService.addUser(user));
    }
}
