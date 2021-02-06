package site.fish.repository.sys;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.User;
import site.fish.repository.BaseRepository;

/**
 * Description: [User Repository]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:35
 */
@Repository
public interface UserRepository extends BaseRepository<User> {
    /**
     * Description: findByUsername
     *
     * @param username : username
     * @return : site.fish119.sss.po.sys.User
     * @author : Morphling
     * @date : 2021/1/27 17:03
     */
    @Query("from User u join fetch u.roles r join fetch r.authorities where u.username=:username")
    User findByUsername(@Param("username") final String username);

    /**
     * Description: 删除指定用户的角色
     *
     * @param id : userId
     * @author : Morphling
     * @date : 2021/2/5 11:26
     */
    @Modifying
    @Query(value = "DELETE FROM sys_user_roles WHERE user_id = :id", nativeQuery = true)
    void deleteUserRoles(@Param("id") Long id);

    /**
     * Description: 设置用户角色
     *
     * @param userId : userId
     * @param roleId : roleId
     * @author : Morphling
     * @date : 2021/2/5 11:27
     */
    @Modifying
    @Query(value = "INSERT IGNORE INTO sys_user_roles VALUES (:userId,:roleId)", nativeQuery = true)
    void insertIgnoreUserRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * Description: 获得指定用户(包含其所有角色列表）
     *
     * @param userId : userId
     * @return : java.util.List<site.fish.vo.sys.IRoleVo>
     * @author : Morphling
     * @date : 2021/2/5 14:19
     */
    @Query("from User u join fetch u.roles where u.id=:userId")
    User getOneWithRole(@Param("userId") Long userId);
}
