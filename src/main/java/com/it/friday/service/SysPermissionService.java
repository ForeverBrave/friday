package com.it.friday.service;

import com.alibaba.fastjson.JSONArray;
import com.it.friday.domain.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 查询所有权限
     * @return
     */
    JSONArray listAllPermission();

    /**
     * 根据roleId查询所有权限
     * @param id
     * @return
     */
    List<SysPermission> listAllPermissionByRoleId(Integer id);

    /**
     * 查询所有菜单
     * @return
     */
    List<SysPermission> menuAll();

    /**
     * 根据id查询菜单
     * @param id
     * @return
     */
    SysPermission getSysPermissionById(Integer id);

    /**
     * 新增菜单
     * @param sysPermission
     * @return
     */
    boolean addPermission(SysPermission sysPermission);

    /**
     * 更新菜单
     * @param sysPermission
     * @return
     */
    boolean editPermission(SysPermission sysPermission);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    boolean deletePermission(Integer id);
}
