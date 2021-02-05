package site.fish.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import site.fish.config.Constant;
import site.fish.entity.sys.User;
import site.fish.service.sys.UserService;
import site.fish.vo.ApiError;
import site.fish.vo.sys.ChangePersonPasswordVo;
import site.fish.vo.sys.UserVo;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Description: [UserController]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/4 12:34
 */
@RestController
@RequestMapping("/api/sys/users")
@Api(tags = "02.用户")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("00.获取用户列表（已分页）")
    @GetMapping("")
    public ResponseEntity<Page<UserVo>> findAllEnabled(
            @PageableDefault(size = Constant.PAGE_SIZE,
                    sort = {Constant.SORT_COLUMN},
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable, true));
    }

    @ApiOperation("01.增加用户")
    @PostMapping("")
    public ResponseEntity<UserVo> addUserTest(@RequestBody @Valid UserVo user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @ApiOperation("02.重置指定用户的密码（默认密码：password）")
    @PutMapping("/{id}/password")
    public ResponseEntity<String> resetUserPassword(@PathVariable final Long id) {
        userService.changeUserPassword(id, Constant.DEFAULT_PASSWORD);
        return ResponseEntity.ok("密码重置成功，默认密码为：" + Constant.DEFAULT_PASSWORD_NO_ENCODE + " ，请尽快通知用户修改密码。");
    }

    @ApiOperation("03.修改当前用户的密码")
    @PostMapping("/changePersonPassword")
    public ResponseEntity<?> changePersonPassword(@RequestBody @Valid ChangePersonPasswordVo vo) {
        if (vo.getNewPassword().equals(vo.getConfirmNewPassword())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userService.changePersonPassword(((User) authentication.getPrincipal()).getId(), vo);
            return ResponseEntity.ok("密码修改成功");
        } else {
            return new ResponseEntity<>(new ApiError(HttpServletResponse.SC_BAD_REQUEST, "两次输入的密码不一致"), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("04.逻辑删除指定用户（设为禁用）")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableUser(@PathVariable final Long id) {
        userService.setUserEnable(id, false);
        return ResponseEntity.ok("用户删除成功");
    }

    @ApiOperation("05.恢复/启用指定用户")
    @PostMapping("/{id}/enable")
    public ResponseEntity<?> enableUser(@PathVariable final Long id) {
        userService.setUserEnable(id, true);
        return ResponseEntity.ok("用户恢复成功");
    }

    @ApiOperation("06.根据Id获取用户信息")
    @GetMapping("/{id}")
    public ResponseEntity<UserVo> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation("07.获取已删除用户列表（已分页）")
    @GetMapping("/disabled")
    public ResponseEntity<Page<UserVo>> findAllDisabled(
            @PageableDefault(size = Constant.PAGE_SIZE,
                    sort = {Constant.SORT_COLUMN},
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(userService.getUsers(pageable, false));
    }

    @ApiOperation("08.设置指定用户的权限")
    @PostMapping("/{id}/roles")
    public ResponseEntity<String> setRoles(@PathVariable final Long id, @RequestBody(required = false) List<Long> roles) {
        userService.setRoles(id, roles);
        return ResponseEntity.ok("权限设置成功");
    }
}
