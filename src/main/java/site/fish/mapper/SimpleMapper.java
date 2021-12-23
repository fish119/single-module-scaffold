package site.fish.mapper;

import site.fish.entity.BaseEntity;
import site.fish.vo.sys.SimpleVo;

/**
 * Description: [仅包含id、name和sort字段的简单Vo对象接口]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/7 19:44
 */
public interface SimpleMapper<T extends BaseEntity> extends BaseMapper<SimpleVo, T> {

}
