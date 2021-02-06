package site.fish.repository.sys;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.Role;
import site.fish.repository.BaseRepository;
import site.fish.vo.sys.IUserVo;

import java.util.List;

/**
 * Description: [Role Repository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:35
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {
    /**
     * Description: findByName
     *
     * @param name : name
     * @return : site.fish119.sss.po.sys.Role
     * @author : Morphling
     * @date : 2021/1/29 10:54
     */
    Role findByName(@Param("name") final String name);

    /**
     * Description: 根据Id获得该角色下用户列表
     *
     * @param roleId : roleId
     * @return : java.util.List<site.fish.vo.sys.IUserVo>
     * @author : Morphling
     * @date : 2021/2/6 13:30
     */
    @Query(value = "SELECT u.id,u.username,u.last_password_reset as lastPasswordReset," +
            "u.is_account_non_expired as accountNonExpired," +
            "u.is_account_non_locked as accountNonLocked, u.is_enabled as enabled ," +
            "u.is_credentials_non_expired as credentialsNonExpired FROM `sys_user` u , sys_user_roles ur " +
            "where u.id = ur.user_id and ur.role_id = :userId and u.is_enabled=1 order by u.created_date",
            nativeQuery = true)
    List<IUserVo> getRoleUsers(@Param("roleId") Long roleId);
}
