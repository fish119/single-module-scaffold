package site.fish.vo.mapper;

import org.mapstruct.Mapper;
import site.fish.entity.sys.Role;
import site.fish.vo.sys.RoleVo;

/**
 * Description: [RoleMapper]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 13:15
 */
@Mapper(componentModel = "spring")
public interface RoleMapper extends BaseMapper<RoleVo, Role>{
}
