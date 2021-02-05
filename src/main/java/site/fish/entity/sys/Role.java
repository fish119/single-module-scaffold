package site.fish.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: [Role Entity]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:34
 */
@EqualsAndHashCode(callSuper = true,exclude = {"authorities","users"})
@Entity()
@Table(name = "sys_role")
@Data
public class Role extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    private Long sort;
    /**
     * 账户是否激活
     */
    @Column(columnDefinition = "bool default true")
    private boolean isEnabled = true;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.PERSIST)
    private Set<User> users = new HashSet<>(0);

    @ManyToMany(targetEntity = Authority.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "Authority_ID"))
    @OrderBy("sort ASC")
    private Set<Authority> authorities = new HashSet<>(0);
}
