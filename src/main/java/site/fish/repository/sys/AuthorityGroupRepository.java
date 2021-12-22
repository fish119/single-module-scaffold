package site.fish.repository.sys;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.AuthorityGroup;
import site.fish.repository.BaseRepository;

import java.util.List;

/**
 * Description: [AuthorityGroupRepository]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/7 10:20
 */
@Repository
@Deprecated
public interface AuthorityGroupRepository extends BaseRepository<AuthorityGroup> {
    /**
     * Description: 查询“权限组”树形结构（仅可用/未删除）
     *
     * @return : Set<AuthorityGroup>
     * @author : Morphling
     * @date : 2021/2/7 15:31
     */
    @EntityGraph(attributePaths = {"children"})
    List<AuthorityGroup> findByParentIsNullAndIsEnabledIsTrue();

    /**
     * Description: 查询“权限组”树形结构（仅可用/未删除）
     *
     * @return : Set<AuthorityGroup>
     * @author : Morphling
     * @date : 2021/2/7 15:31
     */
    @EntityGraph(attributePaths = {"children"})
    @Query("from AuthorityGroup g where g.parent=null and g.isEnabled = TRUE")
    List<AuthorityGroup> findTopEnableGroups();

    /**
     * Description: 查询全部记录的树形结构（含子权限组）
     *
     * @return : Set<AuthorityGroup>
     * @author : Morphling
     * @date : 2021/2/7 15:31
     */
    @Override
    @EntityGraph(attributePaths = {"children"})
    List<AuthorityGroup> findAll();
}
