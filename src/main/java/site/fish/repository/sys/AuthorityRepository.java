package site.fish.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.Authority;

/**
 * Description: [Authority Repository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:35
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Modifying
    @Query(value = "INSERT IGNORE INTO sys_role_authorities VALUES (2,:authorityId)", nativeQuery = true)
    void insertIgnoreAdminAuthorities(@Param("authorityId") Long authorityId);
}
