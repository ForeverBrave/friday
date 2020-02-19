package com.it.friday.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.friday.domain.SysPermission;
import com.it.friday.mapper.SysPermissionMapper;
import com.it.friday.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.friday.utils.TreeUtils;
import org.springframework.stereotype.Service;

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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public JSONArray listAllPermission() {
        List<SysPermission> permissionList = this.baseMapper.selectList(null);
        JSONArray array = new JSONArray();
        TreeUtils.setPermissionsTree(0,permissionList,array);
        return array;
    }

    /**
     * 根据roleId查询所有权限
     * @param id
     * @return
     */
    @Override
    public List<SysPermission> listAllPermissionByRoleId(Integer id) {
        return this.baseMapper.listAllPermissionByRoleId(id);
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<SysPermission> menuAll() {
        return this.baseMapper.selectList(null);
    }

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 新增菜单
     * @param sysPermission
     * @return
     */
    @Override
    public boolean addPermission(SysPermission sysPermission) {
        int insert = this.baseMapper.insert(sysPermission);
        return insert>0;
    }

    /**
     * 更新菜单
     * @param sysPermission
     * @return
     */
    @Override
    public boolean editPermission(SysPermission sysPermission) {
        int update = this.baseMapper.updateById(sysPermission);
        return update>0;
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @Override
    public boolean deletePermission(Integer id) {
        try {
            this.baseMapper.deleteById(id);
            this.baseMapper.deleteByParentId(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
