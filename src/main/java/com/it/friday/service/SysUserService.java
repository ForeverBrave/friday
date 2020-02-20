package com.it.friday.service;

import com.it.friday.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.friday.dto.SysUserDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getUserByName(String username);

    /**
     * 添加用户
     * @param sysUser
     * @param roleId
     * @return
     */
    boolean saveUser(SysUser sysUser, Integer roleId);



    /**
     * 查询电话是否已经被注册
     * @param telephone
     * @return
     */
    SysUser getUserByPhone(String telephone);

    /**
     * 查询邮箱是否已经被注册
     * @param email
     * @return
     */
    SysUser getUserByEmail(String email);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    SysUser getUserById(Integer id);

    /**
     * 更新用户
     * @param sysUser
     * @param roleId
     * @return
     */
    boolean updateUser(SysUser sysUser, Integer roleId);

    /**
     * 删除用户
     * @param user
     * @return
     */
    boolean deleteUser(SysUser user);

    /**
     * 修改密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Integer changePassword(String username, String oldPassword, String newPassword);


}
