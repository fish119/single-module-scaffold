package site.fish.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.fish.config.Constant;
import site.fish.service.sys.RoleService;
import site.fish.vo.sys.RoleVo;

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
}
