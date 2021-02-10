package site.fish.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import site.fish.entity.sys.Authority;
import site.fish.entity.sys.AuthorityTag;
import site.fish.repository.sys.AuthorityRepository;
import site.fish.repository.sys.AuthorityTagRepository;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingBuilderPlugin;
import springfox.documentation.spi.service.contexts.ApiListingContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description: [CustomApiBuilder 根据配置在生成API时将数据同步至数据库的权限表中]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/8 21:56
 */
@Component
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER)
public class CustomApiBuilder implements ApiListingBuilderPlugin {
    public final static String AUTH_JOINER_STR = ";";
    public static List<String> authTags = new ArrayList<>();
    public static List<String> auths = new ArrayList<>();
    /**
     * 是否从Swagger生成权限数据
     */
    @Value("${create-authority-from-swagger}")
    private boolean createFromSwagger;
    /**
     * 是否将权限赋予admin角色
     */
    @Value("${auto-authorize-to-admin}")
    private boolean authorizeToAdmin;
    /**
     * 是否覆盖原有权限数据（清空原有，重新生成）
     */
    @Value("${override-authority-from-swagger}")
    private boolean overrideAuthority;
    /**
     * 是否已情况原有权限数据
     */
    private boolean isAuthCleared = false;

    @Autowired
    private AuthorityTagRepository tagRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void init() {
        if (createFromSwagger) {
            if (!overrideAuthority) {
                tagRepository.findAll().forEach(authTag -> CustomApiBuilder.authTags.add(authTag.getName()));
                authorityRepository.findAll().forEach(authority -> CustomApiBuilder.auths.add(authority.getAuthority()));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void apply(ApiListingContext apiListingContext) {
        if (createFromSwagger) {
            if (overrideAuthority && !isAuthCleared) {
                clearAuthData();
            }
            Set<Tag> tags = apiListingContext.apiListingBuilder().build().getTags();
            List<ApiDescription> apis = apiListingContext.apiListingBuilder().build().getApis();

            saveTags(tags);
            saveAuths(apis);
        }
    }

    /**
     * Description: 清除数据表中原有的权限相关记录（Tag、权限、权限-角色关系）
     *
     * @author : Morphling
     * @date : 2021/2/10 13:22
     */
    @Transactional(rollbackFor = Exception.class)
    private void clearAuthData() {
        authorityRepository.clearAuthorities();
        tagRepository.clearAuthorityTags();
        this.isAuthCleared = true;
    }

    /**
     * Description: 将API中Tag数据保存至数据库权限Tag表
     *
     * @param tags : tags
     * @author : Morphling
     * @date : 2021/2/10 13:22
     */
    private void saveTags(Set<Tag> tags) {
        tags.forEach(tag -> {
            if (overrideAuthority || !CustomApiBuilder.authTags.contains(tag.getName())) {
                AuthorityTag at = new AuthorityTag();
                at.setName(tag.getName());
                at.setDescription(tag.getDescription());
                at.setSort(Integer.parseInt(tag.getName().substring(0, tag.getName().indexOf("."))));
                tagRepository.save(at);
                CustomApiBuilder.authTags.add(at.getName());
            }
        });
    }

    /**
     * Description: 将API中资源数据保存至数据库权限表，并根据配置将其赋予admin角色
     *
     * @param apis : apis
     * @author : Morphling
     * @date : 2021/2/10 13:23
     */
    private void saveAuths(List<ApiDescription> apis) {
        apis.forEach(api -> {
            if (overrideAuthority || !CustomApiBuilder.auths.contains(api.getPath() + CustomApiBuilder.AUTH_JOINER_STR + api.getOperations().get(0).getMethod().name())) {
                Authority authority = new Authority();
                authority.setDescription(api.getDescription());
                authority.setName(api.getOperations().get(0).getSummary());
                authority.setMethod(api.getOperations().get(0).getMethod().name());
                authority.setUrl(api.getPath());
                authority.setSort(Long.parseLong(api.getOperations().get(0).getSummary().substring(0, api.getOperations().get(0).getSummary().indexOf("."))));
                String tagName = api.getOperations().get(0).getTags().stream().findFirst().orElse(null);
                if (!ObjectUtils.isEmpty(tagName)) {
                    AuthorityTag tag = tagRepository.findByName(tagName);
                    authority.setTag(tag);
                }
                authority = authorityRepository.save(authority);
                CustomApiBuilder.auths.add(authority.getAuthority());
                if (authorizeToAdmin) {
                    authorityRepository.insertIgnoreAdminAuthorities(authority.getId());
                }
            }
        });
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
