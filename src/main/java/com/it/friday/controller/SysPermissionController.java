package com.it.friday.controller;


import com.alibaba.fastjson.JSONArray;
import com.it.friday.base.result.Results;
import com.it.friday.domain.SysPermission;
import com.it.friday.dto.SysRoleDto;
import com.it.friday.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/friday/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     */
    @GetMapping("listAllPermission")
    public Results<JSONArray> listAllPermission(){
        JSONArray allPermission = permissionService.listAllPermission();
        return Results.success(allPermission);
    }

    /**
     * 根据roleId查询所有权限
     * @param roleDto
     * @return
     */
    @GetMapping("listAllPermissionByRoleId")
    public Results<SysPermission> listAllPermissionByRoleId(SysRoleDto roleDto){
        List<SysPermission> permissionList = permissionService.listAllPermissionByRoleId(roleDto.getId());
        if(!CollectionUtils.isEmpty(permissionList)){
            return Results.success(0,permissionList);
        }
        return Results.failure();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @GetMapping("menuAll")
    public Results<SysPermission> menuAll(){
        List<SysPermission> permissionList = permissionService.menuAll();
        if (!CollectionUtils.isEmpty(permissionList)) {
            return Results.success(0,permissionList);
        }
        return Results.failure();

    }

    /**
     * 新增菜单
     * @param sysPermission
     * @return
     */
    @PostMapping("add")
    public Results addPermission(@RequestBody SysPermission sysPermission){
        boolean result = permissionService.addPermission(sysPermission);
        return result ? Results.success() : Results.failure();

    }

    /**
     * 更新菜单
     * @return
     */
    @PostMapping("edit")
    public Results editPermission(@RequestBody SysPermission sysPermission){
        boolean result = permissionService.editPermission(sysPermission);
        return result ? Results.success() : Results.failure();
    }

    /**
     * 删除菜单
     * @param sysPermission
     * @return
     */
    @GetMapping("delete")
    public Results deletePermission(SysPermission sysPermission){
        boolean result = permissionService.deletePermission(sysPermission.getId());
        return result ? Results.success() : Results.failure();
    }


}

