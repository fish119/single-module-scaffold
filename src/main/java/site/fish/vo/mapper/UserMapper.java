package site.fish.vo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import site.fish.vo.sys.UserVo;
import site.fish.entity.sys.User;

/**
 * Description: [UserMapper]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/3 16:39
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserVo, User> {
    /**
    * Description: 将Dto转换为Entity ，忽略Password字段
    * @author    : Morphling
    * @date      : 2021/2/3 16:44
    * @param entity : entity
    * @return    : site.fish.dto.sys.UserDto
    */
    @Override
    @Mappings({@Mapping(target = "password", ignore = true)})
    UserVo toVo(User entity);
}