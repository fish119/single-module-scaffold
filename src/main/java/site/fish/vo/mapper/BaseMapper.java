package site.fish.vo.mapper;

import site.fish.entity.BaseEntity;
import site.fish.vo.BaseVo;

import java.util.List;
import java.util.Set;

/**
 * Description: [UserMapper]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/3 15:45
 */
public interface BaseMapper<Vo extends BaseVo, Entity extends BaseEntity> {
    /**
     * Description: 将Entity转换为Dto
     *
     * @param entity : entity
     * @return : Dto
     * @author : Morphling
     * @date : 2021/2/3 16:43
     */
    Vo toVo(Entity entity);

    /**
     * Description: 将Dto转换为Entity
     *
     * @param vo : vo
     * @return : Entity
     * @author : Morphling
     * @date : 2021/2/3 16:43
     */
    Entity toEntity(Vo vo);

    /**
     * Description: 将Entity List转换为Dto List
     *
     * @param entityList : entityList
     * @return : java.util.List<Dto>
     * @author : Morphling
     * @date : 2021/2/3 16:43
     */
    List<Vo> toVoList(List<Entity> entityList);

    Set<Vo> toVoSet(Set<Entity> entityList);

    /**
     * Description: 将Dto List转换为Entity List
     *
     * @param voList : voList
     * @return : java.util.List<Entity>
     * @author : Morphling
     * @date : 2021/2/3 16:44
     */
    List<Entity> toEntityList(List<Vo> voList);

    Set<Entity> toEntityVo(List<Vo> voList);
}
