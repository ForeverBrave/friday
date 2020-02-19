package com.it.friday.mapper;

import com.it.friday.domain.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    int save(@Param("roleId")Integer id, @Param("permissionIds") List<Long> permissionIds);
}
