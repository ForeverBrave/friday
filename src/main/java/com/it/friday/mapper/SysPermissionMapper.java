package com.it.friday.mapper;

import com.it.friday.domain.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据roleId，内连接查询所有菜单
     * @param id
     * @return
     */
    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<SysPermission> listAllPermissionByRoleId(Integer id);

    /**
     * 删除parentId为id的菜单
     * @param id
     * @return
     */
    @Delete("delete from sys_permission where parentId = #{id}")
    int deleteByParentId(Integer id);

    /**
     * 根据userid查询菜单
     * @param userId
     * @return
     */
    @Select("SELECT DISTINCT sp.*  " +
            "FROM sys_role_user sru " +
            "INNER JOIN sys_role_permission srp ON srp.roleId = sru.roleId " +
            "LEFT JOIN sys_permission sp ON srp.permissionId = sp.id " +
            "WHERE " +
            "sru.userId = #{userId}")
    List<SysPermission> listByUserId(@Param("userId") Integer userId);
}
