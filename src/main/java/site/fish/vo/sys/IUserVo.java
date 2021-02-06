package site.fish.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Description: [IUserVo]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 12:27
 */
public interface IUserVo {

    /**
     * Description: getId
     *
     * @return : java.lang.Long
     * @author : Morphling
     * @date : 2021/2/5 14:20
     */
    Long getId();

    /**
     * Description: getName
     *
     * @return : java.lang.String
     * @author : Morphling
     * @date : 2021/2/5 14:20
     */
    String getUsername();

    /**
    * Description: getLastPasswordReset
    * @author    : Morphling
    * @date      : 2021/2/6 13:31
    * @return    : java.util.Date
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date getLastPasswordReset();

    /**
    * Description: getEnabled
    * @author    : Morphling
    * @date      : 2021/2/6 13:31
    * @return    : boolean
    */
    boolean getEnabled();

    /**
    * Description: getAccountNonExpired
    * @author    : Morphling
    * @date      : 2021/2/6 13:32
    * @return    : boolean
    */
    boolean getAccountNonExpired();

    /**
    * Description: getAccountNonLocked
    * @author    : Morphling
    * @date      : 2021/2/6 13:32
    * @return    : boolean
    */
    boolean getAccountNonLocked();

    /**
    * Description: getCredentialsNonExpired
    * @author    : Morphling
    * @date      : 2021/2/6 13:32
    * @return    : boolean
    */
    boolean getCredentialsNonExpired();

}
