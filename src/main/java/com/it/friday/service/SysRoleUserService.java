package com.it.friday.service;

import com.it.friday.domain.SysRoleUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    /**
     * 根据userid查询roleid
     * @param userId
     * @return
     */
    SysRoleUser getRoleUserByUserId(Integer userId);
}
