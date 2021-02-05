package site.fish.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import site.fish.entity.BaseEntity;

/**
 * Description: [BaseRepository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 16:34
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    /**
     * Description: 分页查询所有未删除记录
     *
     * @param pageable : pageable
     * @return : Page<T extends BaseEntity>
     * @author : Morphling
     * @date : 2021/2/4 20:38
     */
    Page<T> findByIsEnabledIsTrue(Pageable pageable);

    /**
     * Description: 分页查询所有已删除记录
     *
     * @param pageable : pageable
     * @return : Page<T extends BaseEntity>
     * @author : Morphling
     * @date : 2021/2/4 21:34
     */
    Page<T> findByIsEnabledIsFalse(Pageable pageable);
}
