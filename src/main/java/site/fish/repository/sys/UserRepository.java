package site.fish.repository.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.User;
import site.fish.vo.sys.IRoleVo;
import site.fish.vo.sys.RoleVo;

import java.util.List;

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
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Description: findByUsername
     *
     * @param username : username
     * @return : site.fish119.sss.po.sys.User
     * @author : Morphling
     * @date : 2021/1/27 17:03
     */
    User findByUsername(@Param("username") final String username);

    /**
     * Description: 分页查询所有未删除用户
     *
     * @param pageable : pageable
     * @return : Page<User>
     * @author : Morphling
     * @date : 2021/2/4 20:38
     */
    Page<User> findByIsEnabledIsTrue(Pageable pageable);

    /**
     * Description: 分页查询所有已删除用户
     *
     * @param pageable : pageable
     * @return : Page<User>
     * @author : Morphling
     * @date : 2021/2/4 21:34
     */
    Page<User> findByIsEnabledIsFalse(Pageable pageable);

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
    * Description: 获得指定用户的所有角色列表
    * @author    : Morphling
    * @date      : 2021/2/5 14:19
    * @param userId : userId
    * @return    : java.util.List<site.fish.vo.sys.IRoleVo>
    */
    @Query(value = "SELECT r.id ,r.name,r.sort FROM `sys_role` r , sys_user_roles ur where r.id = ur.role_id and ur.user_id = :userId order by r.sort", nativeQuery = true)
    List<IRoleVo> getUserRoles(@Param("userId") Long userId);
}
