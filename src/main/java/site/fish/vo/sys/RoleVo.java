package site.fish.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.fish.vo.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * Description: [RoleVo]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 13:14
 */
@ApiModel("基本角色对象（仅包含id、name和sort）")
@Data
public class RoleVo implements BaseVo {
    private Long id;
    @ApiModelProperty("名称")
    @NotEmpty(message = "名称不能为空")
    private String name;
    private Long sort;
}
