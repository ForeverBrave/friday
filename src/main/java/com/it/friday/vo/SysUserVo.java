package com.it.friday.vo;

import com.it.friday.domain.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/16 18:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserVo extends SysUser {

    private static final long serialVersionUID = 1L;

    private Integer page = 1;
    private Integer limit = 10;

}
