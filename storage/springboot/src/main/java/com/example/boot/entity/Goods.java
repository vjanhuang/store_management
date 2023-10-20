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
* Goods实体类
* </p>
*
* 
*/
@Getter
@Setter
@ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // 物品名称
    @ApiModelProperty("物品名称")
    @Alias("物品名称")
    private String name;

    // 物品编号
    @ApiModelProperty("物品编号")
    @Alias("物品编号")
    private String code;

    // 图片
    @ApiModelProperty("图片")
    @Alias("图片")
    private String img;
}