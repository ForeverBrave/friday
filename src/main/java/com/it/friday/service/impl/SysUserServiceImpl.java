package com.it.friday.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.it.friday.domain.SysRoleUser;
import com.it.friday.domain.SysUser;
import com.it.friday.dto.SysUserDto;
import com.it.friday.mapper.SysRoleUserMapper;
import com.it.friday.mapper.SysUserMapper;
import com.it.friday.service.SysRoleUserService;
import com.it.friday.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.it.friday.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public SysUser getUserByName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        SysUser sysUser = this.baseMapper.selectOne(wrapper);
        return sysUser;
    }

    /**
     * 添加用户
     * @param user
     * @param roleId
     * @return
     */
    @Override
    public boolean saveUser(SysUser user, Integer roleId) {
        int insert = 0;

        if(roleId != null){
            //用户表插入
            user.setStatus(true);
            user.setPassword(MD5.crypt(user.getPassword()));
            insert = this.baseMapper.insert(user);
            //权限表插入
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(user.getId());
            sysRoleUserMapper.insert(sysRoleUser);
            return insert>0;
        }else {
            return false;
        }
    }

    /**
     * 查询电话是否已经被注册
     * @param telephone
     * @return
     */
    @Override
    public SysUser getUserByPhone(String telephone) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(telephone),"telephone",telephone);
        SysUser sysUser = this.baseMapper.selectOne(wrapper);
        return sysUser;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Override
    public SysUser getUserById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 更新用户
     * @param user
     * @param roleId
     * @return
     */
    @Override
    public boolean updateUser(SysUser user, Integer roleId) {
        int update = 0;

        if(roleId != null){
            //用户表插入
            update = this.baseMapper.updateById(user);

            //权限表更新
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(user.getId());

            QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
            wrapper.eq("userId",user.getId());
            //判断role_user表中是否已经有值
            SysRoleUser roleUser = sysRoleUserMapper.selectOne(wrapper);
            if(org.springframework.util.StringUtils.isEmpty(roleUser)){
                //没有，则添加
                sysRoleUserMapper.updateById(sysRoleUser);
            }
            //有，则更新
            sysRoleUserMapper.updateById(sysRoleUser);

            return update>0;
        }else {
            return false;
        }
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Override
    public boolean deleteUser(SysUser user) {
        if(org.springframework.util.StringUtils.isEmpty(user)){
            return false;
        }
        //1、删除中间表
        QueryWrapper<SysRoleUser> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",user.getId());
        this.sysRoleUserMapper.delete(wrapper);
        //2、删除用户表
        int delete = this.baseMapper.deleteById(user);
        return delete>0;
    }
}
