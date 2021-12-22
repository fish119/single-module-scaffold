package site.fish.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import site.fish.config.ExceptionMessage;
import site.fish.entity.sys.Role;
import site.fish.entity.sys.User;
import site.fish.repository.sys.RoleRepository;
import site.fish.repository.sys.UserRepository;
import site.fish.service.BaseService;
import site.fish.vo.mapper.RoleMapper;
import site.fish.vo.mapper.UserMapper;
import site.fish.vo.sys.ChangePersonPasswordVo;
import site.fish.vo.sys.RoleVo;
import site.fish.vo.sys.UserVo;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: [UserService]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/2 14:31
 */
@Service
public class UserService extends BaseService<User, UserRepository, UserVo, UserMapper> {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleMapper roleMapper;


    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Description: 增加新用户（仅设置默认角色：user）
     *
     * @param userVo : userVo
     * @return : site.fish.vo.sys.UserVo
     * @author : Morphling
     * @date : 2021/2/2 16:50
     */
    @Transactional(rollbackFor = Exception.class)
    public UserVo addUser(UserVo userVo) {
        User user = mapper.toEntity(userVo);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(userVo.getPassword()));
        if (ObjectUtils.isEmpty(user.getRoles())) {
            Set<Role> roleList = new LinkedHashSet<>();
            roleList.add(roleRepository.getOne(3L));
            user.setRoles(roleList);
        }
        userVo = mapper.toVo(repository.saveAndFlush(user));
        return userVo;
    }


    /**
     * Description: 修改指定用户的密码
     *
     * @param id       : 指定用户的id
     * @param password : 新密码
     * @author : Morphling
     * @date : 2021/2/4 12:46
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeUserPassword(Long id, String password) {
        User user = repository.getOne(id);
        user.setPassword(encoder.encode(password));
        user.setLastPasswordReset(new Date());
        repository.save(user);
    }

    /**
     * Description: 修改指定用户的密码
     *
     * @param id : 用户id
     * @param vo : 修改密码vo
     * @author : Morphling
     * @date : 2021/2/4 12:57
     */
    @Transactional(rollbackFor = Exception.class)
    public void changePersonPassword(Long id, ChangePersonPasswordVo vo) {
        try {
            User user = repository.getOne(id);
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), vo.getOldPassword());
            authenticationManager.authenticate(upToken);
            user.setPassword(encoder.encode(vo.getNewPassword()));
            user.setLastPasswordReset(new Date());
            repository.save(user);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(ExceptionMessage.WRONG_PASSWORD);
        }
    }

    /**
     * Description: 设置用户角色
     *
     * @param id         : id
     * @param roleIdList : roleIdList
     * @author : Morphling
     * @date : 2021/2/5 11:37
     */
    @Transactional(rollbackFor = Exception.class)
    public void setRoles(Long id, List<Long> roleIdList) {
        repository.deleteUserRoles(id);
        if (!ObjectUtils.isEmpty(roleIdList)) {
            roleIdList.forEach(roleId -> repository.insertIgnoreUserRoles(id, roleId));
        }
    }

    /**
     * Description: 获得指定用户的角色列表（未分页）
     *
     * @param id : id
     * @return : java.util.List<site.fish.vo.sys.IRoleVo>
     * @author : Morphling
     * @date : 2021/2/5 21:49
     */
    @Transactional(rollbackFor = Exception.class)
    public Set<RoleVo> getRoles(Long id) {
        return roleMapper.toVoSet(repository.getOneWithRole(id).getRoles());
    }
}