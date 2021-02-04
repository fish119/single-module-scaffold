package site.fish.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: [UserVo]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/2 15:25
 */
@ApiModel("用户对象")
@Data
public class UserVo implements Serializable {
    private Long id;
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码(需经过md5加密)")
    @NotEmpty(message = "用户密码不能为空")
    private String password;
    /**
     * 最后修改密码时间
     */
    private Date lastPasswordReset;

    /**
     * 账户是否未过期
     */
    private boolean isAccountNonExpired = true;
    /**
     * 账户是否未锁定
     */
    private boolean isAccountNonLocked = true;
    /**
     * 密码是否未过期
     */
    private boolean isCredentialsNonExpired = true;
    /**
     * 账户是否激活
     */
    private boolean isEnabled = true;
}
