package com.clm.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clm.common.base.constant.CommonConstant;
import com.clm.common.utils.*;
import com.clm.system.entity.SysDepart;
import com.clm.system.entity.SysUser;
import com.clm.system.model.LoginModel;
import com.clm.system.service.SysDepartService;
import com.clm.system.service.SysUserService;
import com.clm.vo.UserVo;
import com.clm.vo.ResisterUserVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author su
 * @Date 2021/11/24 17:59
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/system")
@Api(tags = "用户注册")
public class RegisterController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<UserVo> register(@RequestBody LoginModel loginModel){
        Result<UserVo> result=new Result<>();
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",loginModel.getUsername());
        SysUser sysUser =sysUserService.getOne(queryWrapper);
        result =(Result<UserVo>) sysUserService.checkRegisterUserIsEffective(sysUser);
        if(!result.isSuccess()) {
            return result;
        }

        ResisterUserVo resisterUserVo = new ResisterUserVo();
        resisterUserVo.setUsername(loginModel.getUsername());
        resisterUserVo.setPassword(loginModel.getPassword());

        String salt = OConvertUtils.randomGen(8);
        resisterUserVo.setSalt(salt);
        String passwordEncode = PasswordUtils.encrypt(resisterUserVo.getUsername(), resisterUserVo.getPassword(), salt);
        resisterUserVo.setPassword(passwordEncode);
        resisterUserVo.setStatus(CommonConstant.USER_UNFREEZE);
        resisterUserVo.setUserIdentity(CommonConstant.USER_IDENTITY_2);
        resisterUserVo.setSex(0); //未知性别

        System.out.println(resisterUserVo.getSex());

        SysUser user=sysUserService.saveUser(resisterUserVo);
//        result.setResult(resisterUserVo);
        result.setCode(ResultCode.SUCCESS);

        //用户登录信息
//        userInfo(user, result);
        return result;
    }

    public Result<UserVo> userInfo(SysUser sysUser,Result<UserVo> result){
        // 获取用户部门信息
//        SysDepart sysDepart = sysDepartService.queryUserDepart(sysUser.getId());
        // 生成token
//        String token = JwtUtils.sign(sysUser.getUsername(), sysUser.getPassword()+sysUser.getSalt());
        // 设置token缓存有效时间
        //TODO 需要拦截完成
//        redisUtils.setKey(CommonConstant.PREFIX_USER_TOKEN+token,token);
//        redisUtils.setExpire(CommonConstant.PREFIX_USER_TOKEN+token,JwtUtils.EXPIRE_TIME* 2 * 24 * 7);
        UserVo userVo=new UserVo();
//        userVo.setToken(token);
//        userVo.setDepart(sysDepart);
        userVo.setUserInfo(sysUser);
        result.setResult(userVo);
        result.success("注册成功");
        return result;
    }
}
