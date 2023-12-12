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
public class UnfreezeController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("解锁")
    @PostMapping("/unfreeze")
    public Result<UserVo> unfreeze(@RequestBody UnfreezeModel unfreezeModel){
        Result<UserVo> result=new Result<>();
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",unfreezeModel.getUsername());
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        result =(Result<UserVo>) sysUserService.checkUnfreezeUserIsEffective(sysUser);
        if(!result.isSuccess()) {
            return result;
        }
//        System.out.println(sysUserService.getErrorNum(unfreezeModel.getUsername())+"=======================");

        String username= unfreezeModel.getUsername();
//        String inputPassword = PasswordUtils.encrypt(unfreezeModel.getUsername(), unfreezeModel.getPassword(), sysUser.getSalt());
        String phonenumber=unfreezeModel.getPhonenumber();
        String mailnumber=unfreezeModel.getMailnumber();

        //判断手机号和邮箱号是否正确
        if(!sysUserService.getUserInfoByUsername(username).getPhone().equals(phonenumber)){
            result.error20001("手机号码错误");
            return result;
        }
        if(!sysUserService.getUserInfoByUsername(username).getEmail().equals(mailnumber)){
            result.error20001("邮箱号码错误");
            return result;
        }

        sysUserService.clearErrorNum(username);
        sysUserService.unfreezeUser(username);

        result.success("解锁成功");
        return result;
    }

}
