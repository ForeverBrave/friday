package com.it.friday.mapper;

import com.it.friday.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Brave
 * @since 2020-02-16
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 更新密码
     * @param id
     * @param password
     * @return
     */
    @Update("update sys_user u set u.password = #{password} where u.id = #{id} ")
    Integer changePassword(@Param("id") Integer id,@Param("password") String password);

}
