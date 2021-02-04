package site.fish.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Description: [ChangePsersonPasswordVo]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/4 12:54
 */
@Data
@ApiModel("修改当前用户密码表单对象")
public class ChangePersonPasswordVo {
    @ApiModelProperty("原密码")
    @NotEmpty(message = "原密码不能为空")
    private String oldPassword;
    @ApiModelProperty("新密码")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
    @ApiModelProperty("确认密码")
    @NotEmpty(message = "确认密码不能为空")
    private String confirmNewPassword;
}
