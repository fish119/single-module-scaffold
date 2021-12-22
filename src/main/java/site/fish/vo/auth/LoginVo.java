package site.fish.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Description: [LoginVo]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 20:13
 */
@Data
@ApiModel("登录表单对象")
public class LoginVo implements Serializable {
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码(需经过md5加密)")
    @NotEmpty(message = "用户密码不能为空")
    private String password;
}
