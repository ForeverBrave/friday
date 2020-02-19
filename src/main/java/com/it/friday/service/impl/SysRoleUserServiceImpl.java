package com.it.friday.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.friday.domain.SysRoleUser;
import com.it.friday.mapper.SysRoleUserMapper;
import com.it.friday.service.SysRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    /**
     * 根据userid查询roleid
     * @param userId
     * @return
     */
    @Override
    public SysRoleUser getRoleUserByUserId(Integer userId) {
        QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        SysRoleUser roleUser = this.baseMapper.selectOne(wrapper);
        return roleUser;
    }
}
