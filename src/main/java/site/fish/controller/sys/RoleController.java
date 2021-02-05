package site.fish.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.fish.config.Constant;
import site.fish.service.sys.RoleService;
import site.fish.vo.sys.RoleVo;

import javax.validation.Valid;

/**
 * Description: [RoleController]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 14:34
 */
@RestController
@RequestMapping("/api/sys/roles")
@Api(tags = "03.角色")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("00.获取角色列表（已分页）")
    @GetMapping("")
    public ResponseEntity<Page<RoleVo>> findAllEnabled(
            @PageableDefault(size = Constant.PAGE_SIZE,
                    sort = {Constant.SORT_COLUMN},
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(roleService.getPages(pageable, true));
    }

    @ApiOperation("01.增加角色")
    @PostMapping("")
    public ResponseEntity<RoleVo> addRole(@RequestBody @Valid RoleVo vo) {
        return ResponseEntity.ok(roleService.editRole(vo, null));
    }

    @ApiOperation("02.编辑角色")
    @PostMapping("/{id}")
    public ResponseEntity<RoleVo> editRole(@PathVariable final Long id, @RequestBody @Valid RoleVo vo) {
        return ResponseEntity.ok(roleService.editRole(vo, id));
    }

    @ApiOperation("03.逻辑删除指定角色（设为禁用）")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableRole(@PathVariable final Long id) {
        roleService.setEnable(id, false);
        return ResponseEntity.ok("角色删除成功");
    }

    @ApiOperation("04.恢复/启用指定角色")
    @PostMapping("/{id}/enable")
    public ResponseEntity<?> enableRole(@PathVariable final Long id) {
        roleService.setEnable(id, true);
        return ResponseEntity.ok("角色恢复成功");
    }

    @ApiOperation("05.根据Id获取用户信息")
    @GetMapping("/{id}")
    public ResponseEntity<RoleVo> getRole(@PathVariable final Long id) {
        return ResponseEntity.ok(roleService.getOneById(id));
    }

}
