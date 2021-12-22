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

/**
 * Description: [BaseService]
 * Copyright  : Copyright (c) 2021

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

    /**
     * Description: 获得分页后的Vo列表
     *
     * @param pageable : pageable
     * @param status   : 状态：true=启用，false=禁用（删除）
     * @return : org.springframework.data.domain.Page<V>
     * @author : Morphling
     * @date : 2021/2/5 21:51
     */
    @Transactional(rollbackFor = Exception.class)
    public Page<V> getPages(Pageable pageable, boolean status) {
        if (status) {
            return repository.findByIsEnabledIsTrue(pageable).map(mapper::toVo);
        } else {
            return repository.findByIsEnabledIsFalse(pageable).map(mapper::toVo);
        }
    }

    /**
     * Description: 根据Id获得Vo对象
     *
     * @param id : Id
     * @return : Vo
     * @author : Morphling
     * @date : 2021/2/4 16:37
     */
    @Transactional(rollbackFor = Exception.class)
    public V getOneById(Long id) {
        E e = repository.getOne(id);
        return (mapper.toVo(e));
    }

    /**
     * Description: 启用（恢复）/禁用（逻辑删除）记录
     *
     * @param id     : id
     * @param status : status
     * @author : Morphling
     * @date : 2021/2/5 22:05
     */
    @Transactional(rollbackFor = Exception.class)
    public void setEnable(Long id, boolean status) {
        E entity = repository.getOne(id);
        entity.setEnabled(status);
        repository.save(entity);
    }
}
