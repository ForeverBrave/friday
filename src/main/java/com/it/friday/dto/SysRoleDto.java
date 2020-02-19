package com.it.friday.dto;

import com.it.friday.domain.SysRole;

import java.util.List;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/18 13:01
 */
public class SysRoleDto extends SysRole {

    private static final long serialVersionUID = -5784234789156935003L;

    private List<Long> permissionIds;

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

}
