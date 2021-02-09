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
 * Description: [CustomApiBuilder ]
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
    public static List<String> authTags = new ArrayList<>();
    public static List<String> auths = new ArrayList<>();
    @Value("${create-authority-from-swagger}")
    private boolean createFromSwagger;
    @Value("${auto-authorize-to-admin}")
    private boolean authorizeToAdmin;

    @Autowired
    private AuthorityTagRepository tagRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void init() {
        if (createFromSwagger) {
            tagRepository.findAll().forEach(authTag -> CustomApiBuilder.authTags.add(authTag.getName()));
            authorityRepository.findAll().forEach(authority -> CustomApiBuilder.auths.add(authority.getAuthority()));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void apply(ApiListingContext apiListingContext) {
        if (createFromSwagger) {
            Set<Tag> tags = apiListingContext.apiListingBuilder().build().getTags();
            saveTags(tags);
            List<ApiDescription> apis = apiListingContext.apiListingBuilder().build().getApis();
            saveAuths(apis);
        }
    }

    private void saveTags(Set<Tag> tags) {
        tags.forEach(tag -> {
            if (!CustomApiBuilder.authTags.contains(tag.getName())) {
                AuthorityTag at = new AuthorityTag();
                at.setName(tag.getName());
                at.setDescription(tag.getDescription());
                at.setSort(Integer.parseInt(tag.getName().substring(0, tag.getName().indexOf("."))));
                tagRepository.save(at);
                CustomApiBuilder.authTags.add(at.getName());
            }
        });
    }

    private void saveAuths(List<ApiDescription> apis) {
        apis.forEach(api -> {
            if (!CustomApiBuilder.auths.contains(api.getPath() + ";" + api.getOperations().get(0).getMethod().name())) {
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
