package site.fish.vo.auth;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Description: [LoginVo]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 20:13
 */
@Data
public class LoginVo {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "用户密码不能为空")
    private String password;
}
