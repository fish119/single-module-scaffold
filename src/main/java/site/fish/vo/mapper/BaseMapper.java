package site.fish.vo.mapper;

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
public interface BaseMapper<Vo, Entity> {
    /**
    * Description: 将Entity转换为Dto
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param entity : entity
    * @return    : Dto
    */
    Vo toVo(Entity entity);

    /**
    * Description: 将Dto转换为Entity
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param vo : vo
    * @return    : Entity
    */
    Entity toEntity(Vo vo);

    /**
    * Description: 将Entity List转换为Dto List
    * @author    : Morphling
    * @date      : 2021/2/3 16:43
    * @param entityList : entityList
    * @return    : java.util.List<Dto>
    */
    List<Vo> toVoList(List<Entity> entityList);

    /**
    * Description: 将Dto List转换为Entity List
    * @author    : Morphling
    * @date      : 2021/2/3 16:44
    * @param voList : voList
    * @return    : java.util.List<Entity>
    */
    List<Entity> toEntityList(List<Vo> voList);

}
