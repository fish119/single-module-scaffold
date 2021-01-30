package site.fish.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TestController {
    @GetMapping("/test/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/api/test")
    public ResponseEntity<String> userTest() {
        return ResponseEntity.ok("User Test");
    }

    @GetMapping("/api/adminTest")
    public ResponseEntity<String> adminTest() {
        return ResponseEntity.ok("Admin Test");
    }
}
