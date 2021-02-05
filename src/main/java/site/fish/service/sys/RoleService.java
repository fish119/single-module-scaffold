package site.fish.service.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish.entity.sys.Role;
import site.fish.repository.sys.RoleRepository;
import site.fish.service.BaseService;
import site.fish.vo.mapper.RoleMapper;
import site.fish.vo.sys.RoleVo;

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
public class RoleService extends BaseService<Role, RoleRepository, RoleVo, RoleMapper> {
    /**
     * Description: 增加/编辑角色
     *
     * @param vo : 角色vo
     * @return : site.fish.vo.sys.RoleVo
     * @author : Morphling
     * @date : 2021/2/5 21:59
     */
    @Transactional(rollbackFor = Exception.class)
    public RoleVo editRole(RoleVo vo, Long id) {
        Role role = mapper.toEntity(vo);
        if (id != null) {
            role.setId(id);
        }
        return mapper.toVo(repository.save(role));
    }
}
