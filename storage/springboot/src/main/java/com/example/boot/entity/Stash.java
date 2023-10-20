package com.example.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* <p>
* Stash实体类
* </p>
*
* 
*/
@Getter
@Setter
@ApiModel(value = "Stash对象", description = "")
public class Stash implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 仓库名称
    @ApiModelProperty("仓库名称")
    @Alias("仓库名称")
    private String name;

    // 仓库编号
    @ApiModelProperty("仓库编号")
    @Alias("仓库编号")
    private String code;

    // 仓库区域
    @ApiModelProperty("仓库区域")
    @Alias("仓库区域")
    private String area;

    // 仓库管理员
    @ApiModelProperty("仓库管理员")
    @Alias("仓库管理员")
    private String manager;
}