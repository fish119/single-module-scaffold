package site.fish.vo.sys;

/**
 * Description: RoleEntity对应Projection对象，供Jpa自定义返回值使用
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 14:14
 */
public interface IRoleVo {

    /**
    * Description: getId
    * @author    : Morphling
    * @date      : 2021/2/5 14:20
    * @return    : java.lang.Long
    */
    Long getId();

    /**
    * Description: getName
    * @author    : Morphling
    * @date      : 2021/2/5 14:20
    * @return    : java.lang.String
    */
    String getName();

    /**
    * Description: getSort
    * @author    : Morphling
    * @date      : 2021/2/5 14:20
    * @return    : java.lang.Long
    */
    Long getSort();
}
