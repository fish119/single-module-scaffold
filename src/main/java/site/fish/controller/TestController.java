package site.fish.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("1.guest测试")
    @GetMapping("/test/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
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
}
