package site.fish.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import site.fish.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: [Authority Entity]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:35
 */
@EqualsAndHashCode(callSuper = true, exclude = {"roles"})
@Entity()
@Table(name = "sys_authority")
@Data
public class Authority extends BaseEntity implements GrantedAuthority {
    private String name;
    private String url;
    private String method;
    private String description;
    private Long sort;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.PERSIST)
    private Set<Role> roles = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id")
    private AuthorityGroup group;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tag_id")
    private AuthorityTag tag;

    @Override
    public String getAuthority() {
        return this.url + ";" + this.method;
    }
}
