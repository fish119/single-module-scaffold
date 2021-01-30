package site.fish.security;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import site.fish.entity.sys.Authority;
import site.fish.entity.sys.User;

import java.util.Collection;

/**
 * Description: [权限验证访问决策管理器]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:25
 */
@Component
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    @Autowired
    private AuthorityCache authorityCache;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
    * 如需指定某路径无需认证：
    * if (!url.contains("/api/")) {
    *   return;
    * }
    */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        String url = ((FilterInvocation) object).getRequestUrl();
        String method = ((FilterInvocation) object).getRequest().getMethod();
        AntPathRequestMatcher matcher;
        if (configAttributes == null) {
            return;
        }
        if(authorityCache.getPermitAllUrls().stream().anyMatch(url::equals)){
            return;
        }
        if(HttpMethod.OPTIONS.matches(method)){
            return;
        }
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (ga instanceof Authority) {
                Authority urlGrantedAuthority = (Authority) ga;
                matcher = new AntPathRequestMatcher(urlGrantedAuthority.getUrl());
                if (matcher.matches(((FilterInvocation) object).getRequest())) {
                    if (method.equalsIgnoreCase(urlGrantedAuthority.getMethod()) || "ALL".equalsIgnoreCase(urlGrantedAuthority.getMethod())) {
                        return;
                    }
                }
            }
        }
        logger.error("403:Forbidden" + "|||Url=" + url + "|||UserId=" + getUserId());
        throw new AccessDeniedException("没有访问权限，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private Long getUserId() {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails.getId();
        } catch (Exception e) {
            return null;
        }
    }
}
