package com.clm.system.mapper;

import com.clm.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clm.system.model.UnfreezeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author su
 * @since 2021-11-22
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select *from sys_user where id=#{userId}")
    SysUser getDeleteUser(@Param("userId") String userId);

    @Select("select error_num from sys_user where username=#{username}")
    int getErrorNum(@Param("username") String username);

    @Update("update sys_user set error_num=error_num+1 where username=#{username}")
    boolean setErrorNum(@Param("username") String username);

    @Update("update sys_user set error_num=0 where username=#{username}")
    boolean clearErrorNum(@Param("username") String username);

    @Update("update sys_user set status=2 where username=#{username}")
    boolean freezeByUsername(@Param("username") String username);

    @Update("update sys_user set status=1 where username=#{username}")
    boolean unFreezeByUsername(@Param("username") String username);

    @Update("update sys_user set phone=#{phonenumber}, email=#{mailnumber} where username=#{username}")
    boolean bindPhoneAndMail(UnfreezeModel bindModel);
}
