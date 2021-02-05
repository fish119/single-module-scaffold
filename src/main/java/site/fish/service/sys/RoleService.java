package site.fish.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.fish.repository.sys.RoleRepository;

/**
 * Description: [RoleService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 13:21
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

}