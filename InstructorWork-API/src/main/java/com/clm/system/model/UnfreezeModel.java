package com.clm.system.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author su
 * @Date 2021/11/24 18:15
 */
@Data
@ApiModel(value="解锁对象", description="解锁对象")
public class UnfreezeModel {
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "手机号")
    private String phonenumber;
    @ApiModelProperty(value = "邮箱号")
    private String mailnumber;
}
