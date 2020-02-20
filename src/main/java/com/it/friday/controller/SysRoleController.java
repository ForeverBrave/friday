package com.it.friday.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.friday.base.result.ResponseCode;
import com.it.friday.base.result.Results;
import com.it.friday.domain.SysRole;
import com.it.friday.domain.SysUser;
import com.it.friday.dto.SysRoleDto;
import com.it.friday.service.SysRoleService;
import com.it.friday.vo.SysRoleVo;
import com.it.friday.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/friday/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/all")
    public Results<SysRole> getAllRoles(){
        List<SysRole> roleList = sysRoleService.getAllRoles();
        return Results.success(50,roleList);
    }

    /**
     * 分页查询角色
     * @param sysRoleVo
     * @return
     */
    @GetMapping("/getAllRoleByPage")
    @PreAuthorize("hasAnyAuthority('sys:role:query')")
    public Results<SysRole> getAllUserByPage(SysRoleVo sysRoleVo){
        //创建page对象，传入两个参数（当前页数，每页记录数）
        IPage<SysRole> pageUser = new Page<>(sysRoleVo.getPage(),sysRoleVo.getLimit());
        //构造条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(sysRoleVo.getName()),"name",sysRoleVo.getName());
        wrapper.orderByAsc("id");
        //调用分页方法查询
        sysRoleService.page(pageUser,wrapper);
        //从page对象中获取分页数据
        //总记录数
        int total = (int) pageUser.getTotal();
        //分页数据
        List<SysRole> records = pageUser.getRecords();

        return Results.success("success",total,records);
    }

    /**
     * 添加角色
     * @return
     */
    @PostMapping("/add")
    public Results addRole(@RequestBody SysRoleDto sysRoleDto){
        boolean result = sysRoleService.addRole(sysRoleDto);
        if(result){
            return Results.success();
        }
        return Results.failure();
    }

    /**
     * 更新角色
     * @return
     */
    @PostMapping("/edit")
    public Results editRole(@RequestBody SysRoleDto sysRoleDto){
        boolean result = sysRoleService.editRole(sysRoleDto);
        if (result) {
            return Results.success();
        }
        return Results.failure();
    }

    /**
     * 删除角色
     * @param sysRoleDto
     * @return
     */
    @GetMapping("delete")
    @PreAuthorize("hasAnyAuthority('sys:role:del')")
    public Results deleteRole(SysRoleDto sysRoleDto){
        boolean result = sysRoleService.deleteRole(sysRoleDto.getId());
        if (result) {
            return Results.success();
        }else {
            return Results.failure(ResponseCode.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USER_ROLE_NO_CLEAR.getMessage());
        }
    }

}

