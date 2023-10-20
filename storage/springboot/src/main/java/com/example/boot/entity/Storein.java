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
* Storein实体类
* </p>
*
* 
*/
@Getter
@Setter
@ApiModel(value = "Storein对象", description = "")
public class Storein implements Serializable {

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

    // 物品名称
    @ApiModelProperty("物品名称")
    @Alias("物品名称")
    private String goods;

    // 物品编号
    @ApiModelProperty("物品编号")
    @Alias("物品编号")
    private String goodscode;

    // 库存量
    @ApiModelProperty("库存量")
    @Alias("库存量")
    private Integer num;

    private String date;
}