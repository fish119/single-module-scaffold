package site.fish.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.Role;

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
public interface RoleRepository extends JpaRepository<Role,Long> {
    /**
    * Description: findByName
    * @author    : Morphling
    * @date      : 2021/1/29 10:54
    * @param name : name
    * @return    : site.fish119.sss.po.sys.Role
    */
    Role findByName(@Param("name") final String name);
}
