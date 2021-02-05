package site.fish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import site.fish.entity.BaseEntity;
import site.fish.repository.BaseRepository;
import site.fish.vo.BaseVo;
import site.fish.vo.mapper.BaseMapper;
import site.fish.vo.sys.RoleVo;

/**
 * Description: [BaseService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 16:37
 */
public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>, V extends BaseVo, M extends BaseMapper<V, E>> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected R repository;

    @Autowired
    protected M mapper;

    @Transactional(rollbackFor = Exception.class)
    public Page<V> getPages(Pageable pageable, boolean status) {
        if (status) {
            return repository.findByIsEnabledIsTrue(pageable).map(mapper::toVo);
        } else {
            return repository.findByIsEnabledIsFalse(pageable).map(mapper::toVo);
        }
    }
}
