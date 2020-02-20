package com.it.friday.vo;

import com.it.friday.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/20 17:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ChangePasswordVo extends SysUser {

    private static final long serialVersionUID = 1L;

    private String oldPassword;
    private String newPassword;

}
