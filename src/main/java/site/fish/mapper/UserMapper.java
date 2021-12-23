package site.fish.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import site.fish.entity.sys.User;
import site.fish.vo.sys.UserVo;

/**
 * Description: [UserMapper]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/3 16:39
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserVo, User> {
    /**
     * Description: 将Dto转换为Entity ，忽略Password字段
     *
     * @param entity : entity
     * @return : site.fish.dto.sys.UserDto
     * @author : Morphling
     * @date : 2021/2/3 16:44
     */
    @Override
    @Mappings({@Mapping(target = "password", ignore = true)})
    UserVo toVo(User entity);
}