package site.fish.repository.sys;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.Role;
import site.fish.repository.BaseRepository;

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
     * Description: 根据Id获得角色信息（包含其所有用户列表）
     *
     * @param roleId : roleId
     * @return : java.util.List<site.fish.vo.sys.IUserVo>
     * @author : Morphling
     * @date : 2021/2/6 13:30
     */
    @Query("from Role r join fetch r.users where r.id=:roleId")
    Role getOneWithUsers(@Param("roleId") Long roleId);
}
