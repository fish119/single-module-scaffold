package site.fish.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.fish.entity.sys.User;

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
    * @author    : Morphling
    * @date      : 2021/1/27 17:03
    * @param username : username
    * @return    : site.fish119.sss.po.sys.User
    */
    User findByUsername(@Param("username") final String username);
}
