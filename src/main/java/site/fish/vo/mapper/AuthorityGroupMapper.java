package site.fish.vo.mapper;

import org.mapstruct.Mapper;
import site.fish.entity.sys.AuthorityGroup;
import site.fish.vo.sys.AuthorityGroupVo;

/**
 * Description: [AuthorityGroupMapper]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 20:52
 */
@Mapper(componentModel = "spring")
public interface AuthorityGroupMapper extends BaseMapper<AuthorityGroupVo, AuthorityGroup> {

}
