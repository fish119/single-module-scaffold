package site.fish.service.sys;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import site.fish.dto.mapper.BaseMapper;
import site.fish.dto.mapper.UserMapper;
import site.fish.entity.sys.Role;
import site.fish.entity.sys.User;
import site.fish.repository.sys.RoleRepository;
import site.fish.repository.sys.UserRepository;
import site.fish.dto.sys.UserDto;

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
    UserMapper mapper;

    /**
    * Description: 增加新用户（仅设置默认角色：user）
    * @author    : Morphling
    * @date      : 2021/2/2 16:50
    * @param userDto : userDto
    * @return    : site.fish.vo.sys.UserVo
    */
    @Transactional(rollbackFor = Exception.class)
    public UserDto addUser(UserDto userDto) {
//        BeanUtils.copyProperties(userDto,user);
        User user = mapper.toEntity(userDto);
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(userDto.getPassword()));
        if(ObjectUtils.isEmpty(user.getRoles())){
            Set<Role> roleList = new LinkedHashSet<>();
            roleList.add(roleRepository.getOne(3L));
            user.setRoles(roleList);
        }
        userDto = mapper.toDto(userRepository.saveAndFlush(user));
//        BeanUtils.copyProperties(userRepository.saveAndFlush(user),userDto,"password");
        return userDto;
    }
}


