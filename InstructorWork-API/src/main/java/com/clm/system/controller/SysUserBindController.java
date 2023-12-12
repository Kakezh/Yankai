package com.clm.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clm.common.utils.JwtUtils;
import com.clm.common.utils.PasswordUtils;
import com.clm.common.utils.RedisUtils;
import com.clm.common.utils.Result;
import com.clm.system.entity.SysDepart;
import com.clm.system.entity.SysUser;
import com.clm.system.model.LoginModel;
import com.clm.system.model.UnfreezeModel;
import com.clm.system.service.SysDepartService;
import com.clm.system.service.SysUserService;
import com.clm.vo.UserVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author su
 * @Date 2021/11/24 17:59
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/system")
@Api(tags = "用户解冻")
public class SysUserBindController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("绑定手机号或邮箱号")
    @PostMapping("/bind")
    public Result<UserVo> bind(@RequestBody UnfreezeModel bindModel){
        Result<UserVo> result=new Result<>();

        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",bindModel.getUsername());
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        if(sysUser==null){
            result.error20001("未找到此用户，请重试");
            return result;
        }

        String username= bindModel.getUsername();
        String phonenumber=bindModel.getPhonenumber();
        String mailnumber=bindModel.getMailnumber();

        try{
            if(sysUserService.bindPhoneAndMail(bindModel)){
                result.success("绑定成功");
                return result;
            }
        }catch(Exception e){
            result.error20001("手机号或邮箱号重复，请重试");
            return result;
        }

        result.error20001("绑定失败请重试");
        return result;
    }

}
