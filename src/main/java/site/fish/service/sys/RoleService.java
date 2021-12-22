package site.fish.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish.entity.sys.Role;
import site.fish.repository.sys.RoleRepository;
import site.fish.service.BaseService;
import site.fish.vo.mapper.RoleMapper;
import site.fish.vo.mapper.UserMapper;
import site.fish.vo.sys.RoleVo;
import site.fish.vo.sys.UserVo;

import java.util.Set;

/**
 * Description: [RoleService]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/5 13:21
 */
@Service
public class RoleService extends BaseService<Role, RoleRepository, RoleVo, RoleMapper> {
    @Autowired
    private UserMapper userMapper;

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


    /**
     * Description: 获得指定用户的角色列表（未分页）
     * @author    : Morphling
     * @date      : 2021/2/5 21:49
     * @param id : id
     * @return    : java.util.List<site.fish.vo.sys.IRoleVo>
     */
    @Transactional(rollbackFor = Exception.class)
    public Set<UserVo> getUsers(Long id) {
        return userMapper.toVoSet(repository.getOneWithUsers(id).getUsers());
    }
}
