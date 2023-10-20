package com.example.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* User实体类
* </p>
*
* 
*/
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 用户名
    @ApiModelProperty("用户名")
    @Alias("用户名")
    private String username;

    // 密码
    @ApiModelProperty("密码")
    @Alias("密码")
    private String password;

    // 名称
    @ApiModelProperty("名称")
    @Alias("名称")
    private String name;

    // 性别
    @ApiModelProperty("性别")
    @Alias("性别")
    private String sex;

    // 生日
    @ApiModelProperty("生日")
    @Alias("生日")
    private String birth;

    // 手机
    @ApiModelProperty("手机")
    @Alias("手机")
    private String phone;

    // 地址
    @ApiModelProperty("地址")
    @Alias("地址")
    private String address;

    @ApiModelProperty("角色")
    @Alias("角色")
    private String role;

    @ApiModelProperty("头像")
    @Alias("头像")
    private String avatar;

    @ApiModelProperty("禁用")
    @Alias("禁用")
    private Boolean disable;

    @TableField(exist = false)
    private String newPass;

    @TableField(exist = false)
    private String token;
}