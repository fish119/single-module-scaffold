package site.fish.service.sys;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish.entity.sys.AuthorityGroup;
import site.fish.repository.sys.AuthorityGroupRepository;
import site.fish.service.BaseService;
import site.fish.vo.mapper.AuthorityGroupMapper;
import site.fish.vo.mapper.SimpleMapper;
import site.fish.vo.sys.AuthorityGroupVo;
import site.fish.vo.sys.SimpleVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: [AuthorityGroupService]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/6 20:52
 */
@Service
@Deprecated
public class AuthorityGroupService extends BaseService<AuthorityGroup, AuthorityGroupRepository, AuthorityGroupVo, AuthorityGroupMapper> {
    @Autowired
    private SimpleAuthorityGroupMapper simpleMapper;

    /**
     * Description: 获得所有可用权限组的树形结构
     * 考虑效率问题，暂时取消使用该方法，改用getTree()方法
     *
     * @return : java.util.List<site.fish.vo.sys.AuthorityGroupVo>
     * @author : Morphling
     * @date : 2021/2/7 16:29
     */
    @Deprecated
    @Transactional(rollbackFor = Exception.class)

    public List<AuthorityGroupVo> getPageTree() {
        return mapper.toVoList(repository.findByParentIsNullAndIsEnabledIsTrue());
    }

    /**
     * Description: 获得所有可用权限组的树形结构
     *
     * @return : List<AuthorityGroupVo>
     * @author : Morphling
     * @date : 2021/2/7 16:29
     */
    @Transactional(rollbackFor = Exception.class)
    public List<AuthorityGroupVo> getTree() {
        return mapper.toVoList(repository.findAll().stream()
                .filter(ag -> ag.getParent() == null && ag.isEnabled())
                .collect(Collectors.toList()));
    }

    /**
     * Description: 根据ID返回基本权限组对象（仅包含id、name和sort）
     *
     * @param id : id
     * @return : site.fish.vo.sys.SimpleVo（基本对象，仅包含id、name和sort）
     * @author : Morphling
     * @date : 2021/2/7 20:56
     */
    @Transactional(rollbackFor = Exception.class)
    public SimpleVo getSimpleOneById(Long id) {
        return (simpleMapper.toVo(repository.getOne(id)));
    }

    /**
     * Description: 增加/编辑 权限组（仅可编辑name、sort）
     *
     * @param vo : vo
     * @param id : id
     * @return : site.fish.vo.sys.SimpleVo
     * @author : Morphling
     * @date : 2021/2/7 21:07
     */
    @Transactional(rollbackFor = Exception.class)
    public SimpleVo editAuthorityGroup(SimpleVo vo, Long id) {
        AuthorityGroup ag = simpleMapper.toEntity(vo);
        if (id != null) {
            ag.setId(id);
        }
        return simpleMapper.toVo(repository.save(ag));
    }
}

@Mapper(componentModel = "spring")
interface SimpleAuthorityGroupMapper extends SimpleMapper<AuthorityGroup> {

}
