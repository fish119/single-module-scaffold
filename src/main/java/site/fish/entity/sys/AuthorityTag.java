package site.fish.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description: [AuthorityTag]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/9 11:34
 */
@EqualsAndHashCode(callSuper = true, exclude = {"authorities"})
@Entity()
@Table(name = "sys_authority_tag")
@Data
public class AuthorityTag extends BaseEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    private String description;
    private int sort;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "tag_id")
    @OrderBy("sort ASC")
    private Set<Authority> authorities = new HashSet<>(0);

}
