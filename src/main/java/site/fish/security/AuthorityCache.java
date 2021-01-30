package site.fish.security;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.fish.repository.sys.RoleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: [AuthorityCache]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:26
 */
@Component
public class AuthorityCache {
    public static List<String> permitAllUrls = null;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public List<String> getPermitAllUrls() {
        if (AuthorityCache.permitAllUrls == null) {
            AuthorityCache.permitAllUrls = new ArrayList<>();
            repository.findByName("guest").getAuthorities().forEach(authority -> AuthorityCache.permitAllUrls.add(authority.getUrl()));
        }
        return AuthorityCache.permitAllUrls;
    }
}
