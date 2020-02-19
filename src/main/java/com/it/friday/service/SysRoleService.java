package com.it.friday.service;

import com.it.friday.base.result.Results;
import com.it.friday.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.it.friday.dto.SysRoleDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> getAllRoles();

    /**
     * 添加角色
     * @return
     */
    boolean addRole(SysRoleDto sysRoleDto);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    SysRole getRoleById(Integer id);

    /**
     * 更新角色
     * @param sysRoleDto
     * @return
     */
    boolean editRole(SysRoleDto sysRoleDto);

    /**
     * 删除角色
     * @param id
     * @return
     */
    boolean deleteRole(Integer id);
}
