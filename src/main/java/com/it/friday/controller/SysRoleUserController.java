package com.it.friday.controller;


import com.it.friday.base.result.Results;
import com.it.friday.domain.SysRoleUser;
import com.it.friday.service.SysRoleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
@RestController
@RequestMapping("/friday/roleuser")
public class SysRoleUserController {

    @Autowired
    private SysRoleUserService roleUserService;

    /**
     * 根据userid查询roleid
     * @param userId
     * @return
     */
    @PostMapping("/getRoleUserByUserId")
    public Results getRoleUserByUserId(Integer userId){
        SysRoleUser roleUser = roleUserService.getRoleUserByUserId(userId);
        if (roleUser != null) {
            return Results.success(roleUser);
        }
        return Results.success();
    }

}

