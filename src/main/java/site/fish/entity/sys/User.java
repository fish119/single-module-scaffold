package site.fish.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.fish.entity.BaseEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: [User Entity]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:29
 */
@EqualsAndHashCode(callSuper = true,exclude = {"roles"})
@Entity
@Table(name = "sys_user")
@Data
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @JsonIgnore
    @Column(nullable = false, length = 68)
    private String password;

    /**
     * 最后修改密码时间
     */
    private Date lastPasswordReset;

    /**
     * 账户是否未过期
     */
    @Column(columnDefinition = "bool default true")
    private boolean isAccountNonExpired = true;
    /**
     * 账户是否未锁定
     */
    @Column(columnDefinition = "bool default true")
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否未过期
     */
    @Column(columnDefinition = "bool default true")
    private boolean isCredentialsNonExpired = true;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    @OrderBy("sort ASC")
    private Set<Role> roles = new HashSet<>(0);

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        for (Role role : this.roles) {
            authorities.addAll(role.getAuthorities());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
