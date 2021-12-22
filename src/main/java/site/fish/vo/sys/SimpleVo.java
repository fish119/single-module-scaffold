package site.fish.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.fish.vo.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * Description: [SimpleVo]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/7 19:43
 */
@ApiModel("基本Vo对象（仅包含id、name和sort）")
@Data
public class SimpleVo implements BaseVo {
    private Long id;
    @ApiModelProperty("名称")
    @NotEmpty(message = "名称不能为空")
    private String name;
    private Long sort;
}
