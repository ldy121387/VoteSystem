package com.example.equipmentmanagent.DTO;

import com.baomidou.mybatisplus.annotation.*;
import jnr.ffi.annotations.In;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "vote")//指定表名
public class Vote {
    //value与数据库主键列名一致，若实体类属性名与表主键列名一致可省略value
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private String name;
    private String content;
    private Integer type;
    private String startTime;
    private String endTime;
    private Integer number;
    private String pk;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer deleted;
}
