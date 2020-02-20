package com.it.friday.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.friday.base.result.ResponseCode;
import com.it.friday.base.result.Results;
import com.it.friday.domain.SysUser;
import com.it.friday.dto.SysUserDto;
import com.it.friday.service.SysUserService;
import com.it.friday.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/friday/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/{username}")
    public SysUser getUserByName(@PathVariable String username){
        SysUser user = sysUserService.getUserByName(username);
        return user;
    }

    @GetMapping("/list")
    public Results<SysUser> getAllUser(){
        List<SysUser> users = new ArrayList<>();
        users.add(sysUserService.getUserByName("admin"));
        return Results.success("success",1000,users);
    }

    /**
     * 分页查询用户
     * @param sysUserVo
     * @return
     */
    @GetMapping("/getAllUserByPage")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public Results<SysUser> getAllUserByPage(SysUserVo sysUserVo){
        //创建page对象，传入两个参数（当前页数，每页记录数）
        IPage<SysUser> pageUser = new Page<>(sysUserVo.getPage(),sysUserVo.getLimit());
        //构造条件
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.like(org.apache.commons.lang3.StringUtils.isNotEmpty(sysUserVo.getUsername()),"username",sysUserVo.getUsername());
        wrapper.orderByAsc("id");
        //调用分页方法查询
        sysUserService.page(pageUser,wrapper);
        //从page对象中获取分页数据
        //总记录数
        int total = (int) pageUser.getTotal();
        //分页数据
        List<SysUser> records = pageUser.getRecords();

        return Results.success("success",total,records);
    }

    /**
     * 添加用户
     * @param sysUserDto
     * @param roleId
     * @return
     */
    @PostMapping("/add")
    public Results<SysUser> saveUser(SysUserDto sysUserDto,Integer roleId){

        SysUser sysUser = null;
        sysUser = sysUserService.getUserByPhone(sysUserDto.getTelephone());
        //1、判断前台传来的手机号是否已经注册
        if (!StringUtils.isEmpty(sysUser) && !(sysUser.getId().equals(sysUserDto.getId()))) {
            //是，则返回失败
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }

        //2、否，则添加到用户表中
        boolean result = sysUserService.saveUser(sysUserDto,roleId);
        if(result){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    /**
     * 更新用户
     * @param sysUserDto
     * @param roleId
     * @return
     */
    @PostMapping("/edit")
    public Results<SysUser> updateUser(SysUserDto sysUserDto,Integer roleId){

        SysUser sysUser = null;
        sysUser = sysUserService.getUserByPhone(sysUserDto.getTelephone());
        //1、判断前台传来的手机号是否已经注册
        if (!StringUtils.isEmpty(sysUser) && !(sysUser.getId().equals(sysUserDto.getId()))) {
            //是，则返回失败
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }

        //2、否，则更新到用户表中
        boolean result = sysUserService.updateUser(sysUserDto,roleId);
        if(result){
            return Results.success();
        }else {
            return Results.failure();
        }
    }

    /**
     * 删除用户
     * @param userDto
     * @return
     */
    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('sys:user:del')")
    public Results deleteUser(SysUserDto userDto){
        boolean result = sysUserService.deleteUser(userDto);
        if (result) {
            return Results.success();
        }else {
            return Results.failure();
        }
    }

}

