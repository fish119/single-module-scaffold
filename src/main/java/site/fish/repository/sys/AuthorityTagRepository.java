package site.fish.repository.sys;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.AuthorityTag;
import site.fish.repository.BaseRepository;

/**
 * Description: [AuthorityTagRepository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/9 13:19
 */
@Repository
public interface AuthorityTagRepository extends BaseRepository<AuthorityTag> {
    /**
     * Description: 根据Tag名称查询Tag
     *
     * @param name : name
     * @return : site.fish.entity.sys.AuthorityTag
     * @author : Morphling
     * @date : 2021/2/9 16:36
     */
    AuthorityTag findByName(String name);

    /**
     * Description: 清空权限Tag表（慎用）
     * 必须在AuthorityRepository.clearAuthorityTags后使用
     *
     * @author : Morphling
     * @date : 2021/2/10 10:40
     */
    @Modifying
    @Query(value = "delete sys_authority_tag from sys_authority_tag", nativeQuery = true)
    void clearAuthorityTags();
}
