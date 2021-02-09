package site.fish.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: [AuthorityGroup]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 20:19
 */
@EqualsAndHashCode(callSuper = true, exclude = {"parent", "children", "authorities"})
@Entity()
@Table(name = "sys_authority_group")
@Data
@Deprecated
public class AuthorityGroup extends BaseEntity {
    @Column(nullable = false, unique = true, length = 20)
    private String name;
    private Long sort;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private AuthorityGroup parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    @OrderBy("sort ASC")
    private Set<AuthorityGroup> children = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    @OrderBy("sort ASC")
    private Set<Authority> authorities = new HashSet<>(0);
}
