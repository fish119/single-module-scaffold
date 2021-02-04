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
import site.fish.vo.mapper.UserMapper;
import site.fish.vo.sys.ChangePersonPasswordVo;
import site.fish.vo.sys.UserVo;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description: [UserService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/2 14:31
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserMapper mapper;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Description: 增加新用户（仅设置默认角色：user）
     *
     * @param userDto : userDto
     * @return : site.fish.vo.sys.UserVo
     * @author : Morphling
     * @date : 2021/2/2 16:50
     */
    @Transactional(rollbackFor = Exception.class)
    public UserVo addUser(UserVo userDto) {
        User user = mapper.toEntity(userDto);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(userDto.getPassword()));
        if (ObjectUtils.isEmpty(user.getRoles())) {
            Set<Role> roleList = new LinkedHashSet<>();
            roleList.add(roleRepository.getOne(3L));
            user.setRoles(roleList);
        }
        userDto = mapper.toVo(userRepository.saveAndFlush(user));
        return userDto;
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
        User user = userRepository.getOne(id);
        user.setPassword(encoder.encode(password));
        user.setLastPasswordReset(new Date());
        userRepository.save(user);
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
            User user = userRepository.getOne(id);
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), vo.getOldPassword());
            authenticationManager.authenticate(upToken);
            user.setPassword(encoder.encode(vo.getNewPassword()));
            userRepository.save(user);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException(ExceptionMessage.WRONG_PASSWORD);
        }
    }
}


