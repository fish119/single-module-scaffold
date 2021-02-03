package site.fish.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import site.fish.dto.sys.UserDto;
import site.fish.entity.sys.User;

import java.util.List;

/**
 * Description: [UserMapper]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/3 15:45
 */
public interface BaseMapper<Dto, Entity> {
    /**
    * Description: 将Entity转换为Dto
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param entity : entity
    * @return    : Dto
    */
    Dto toDto(Entity entity);

    /**
    * Description: 将Dto转换为Entity
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param dto : dto
    * @return    : Entity
    */
    Entity toEntity(Dto dto);

    /**
    * Description: 将Entity List转换为Dto List
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param entityList : entityList
    * @return    : java.util.List<Dto>
    */
    List<Dto> toDtoList(List<Entity> entityList);

    /**
    * Description: 将Dto List转换为Entity List
    * @author    : Morphling
    * @date      : 2021/2/3 16:44
    * @param dtoList : dtoList
    * @return    : java.util.List<Entity>
    */
    List<Entity> toEntityList(List<Dto> dtoList);
}
