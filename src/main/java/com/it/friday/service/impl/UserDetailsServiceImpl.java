package com.it.friday.service.impl;

import com.it.friday.domain.SysPermission;
import com.it.friday.domain.SysUser;
import com.it.friday.dto.LoginUser;
import com.it.friday.mapper.SysPermissionMapper;
import com.it.friday.service.SysPermissionService;
import com.it.friday.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/20 13:45
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByName(username);
        if(sysUser == null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        }else if(sysUser.getStatus() == 0){
            throw new LockedException("用户被锁定，请联系管理员");
        }

        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser,loginUser);

        List<SysPermission> permissionList = permissionMapper.listByUserId(sysUser.getId());
        loginUser.setPermissions(permissionList);

        return loginUser;
    }
}
