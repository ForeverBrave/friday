package com.it.friday.vo;

import com.it.friday.domain.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/18 10:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleVo extends SysRole {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;

}
