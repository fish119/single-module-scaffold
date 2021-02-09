package site.fish.repository.sys;

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
    * @author    : Morphling
    * @date      : 2021/2/9 16:36
    * @param name : name
    * @return    : site.fish.entity.sys.AuthorityTag
    */
    AuthorityTag findByName(String name);
}
