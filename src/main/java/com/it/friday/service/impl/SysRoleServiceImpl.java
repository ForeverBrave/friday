package com.it.friday.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.friday.base.result.Results;
import com.it.friday.domain.SysRole;
import com.it.friday.domain.SysRolePermission;
import com.it.friday.domain.SysRoleUser;
import com.it.friday.dto.SysRoleDto;
import com.it.friday.mapper.SysRoleMapper;
import com.it.friday.mapper.SysRolePermissionMapper;
import com.it.friday.mapper.SysRoleUserMapper;
import com.it.friday.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePermissionMapper rolePermissionMapper;

    @Autowired
    private SysRoleUserMapper roleUserMapper;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<SysRole> getAllRoles() {
        return this.baseMapper.selectList(null);
    }

    /**
     * 添加角色
     * @param sysRoleDto
     * @return
     */
    @Override
    public boolean addRole(SysRoleDto sysRoleDto) {
        //1、先保存角色
        this.baseMapper.insert(sysRoleDto);
        List<Long> permissionIds = sysRoleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0);
        //2、保存角色对应的所有权限
        if (!CollectionUtils.isEmpty(permissionIds)) {
            //mapper层面循环遍历
            this.rolePermissionMapper.save(sysRoleDto.getId(), permissionIds);
            return true;
        }
        return false;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @Override
    public SysRole getRoleById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 更新角色
     * @param sysRoleDto
     * @return
     */
    @Override
    public boolean editRole(SysRoleDto sysRoleDto) {

        List<Long> permissionIds = sysRoleDto.getPermissionIds();
        //移除0,permission id是从1开始
        permissionIds.remove(0);

        //1、更新角色权限之前要删除该角色之前的所有权限
        rolePermissionMapper.deleteById(sysRoleDto.getId());

        //2、判断该角色是否有赋予权限值，有就添加
        if (!CollectionUtils.isEmpty(permissionIds)) {
            this.rolePermissionMapper.save(sysRoleDto.getId(), permissionIds);
        }

        //3、更新角色表
        int update = this.baseMapper.updateById(sysRoleDto);
        return update>0;
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public boolean deleteRole(Integer id) {
        //1、查看是否有用户关联此角色
        QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("roleId",id);
        List<SysRoleUser> sysRoleUsers = roleUserMapper.selectList(wrapper);
        //没有，则删除
        if (sysRoleUsers.size()<=0) {
            this.baseMapper.deleteById(id);
            return true;
        }
        //有，则删除失败
        return false;
    }
}
