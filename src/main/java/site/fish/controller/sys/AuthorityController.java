package site.fish.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish.service.sys.AuthorityGroupService;
import site.fish.vo.sys.AuthorityGroupVo;
import site.fish.vo.sys.SimpleVo;

import javax.validation.Valid;
import java.util.List;

/**
 * Description: [AuthorityController]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 20:40
 */
@RestController
@RequestMapping("/api/sys/authorities")
@Api(tags = "04.权限")
public class AuthorityController {
    @Autowired
    AuthorityGroupService groupService;

    @ApiOperation("00.获取所有可用权限组（树形结构）")
    @GetMapping("/groups")
    public ResponseEntity<List<AuthorityGroupVo>> findAllEnabled() {
        return ResponseEntity.ok(groupService.getTree());
    }

    @ApiOperation("01.根据ID获得权限组信息（不含子权限组）")
    @GetMapping("/groups/{id}")
    public ResponseEntity<SimpleVo> getOne(@PathVariable final Long id) {
        return ResponseEntity.ok(groupService.getSimpleOneById(id));
    }

    @ApiOperation("02.新增权限组")
    @PostMapping("/groups")
    public ResponseEntity<SimpleVo> addOne(@RequestBody @Valid SimpleVo vo) {
        return ResponseEntity.ok(groupService.editAuthorityGroup(vo, null));
    }

    @ApiOperation("03.编辑权限组")
    @PostMapping("/groups/{id}")
    public ResponseEntity<SimpleVo> editOne(@PathVariable final Long id, @RequestBody @Valid SimpleVo vo) {
        return ResponseEntity.ok(groupService.editAuthorityGroup(vo, id));
    }
}
