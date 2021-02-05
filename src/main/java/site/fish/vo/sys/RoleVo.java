package site.fish.vo.sys;

import lombok.Data;
import site.fish.vo.BaseVo;

/**
 * Description: [RoleVo]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 13:14
 */
@Data
public class RoleVo implements BaseVo {
    private Long id;
    private String name;
    private Long sort;
}
