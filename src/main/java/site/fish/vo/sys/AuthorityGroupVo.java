package site.fish.vo.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import site.fish.vo.BaseVo;

import java.util.Set;

/**
 * Description: [AuthorityGroupVo]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 20:53
 */
@EqualsAndHashCode(exclude = {"children"})
@Data
public class AuthorityGroupVo implements BaseVo {
    private Long id;
    private String name;
    private Long sort;
    private Set<AuthorityGroupVo> children;
}
