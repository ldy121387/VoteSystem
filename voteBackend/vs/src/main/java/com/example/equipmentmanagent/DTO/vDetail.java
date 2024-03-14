package com.example.equipmentmanagent.DTO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "vDetail")//指定表名
public class vDetail {
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    private Integer voteId;
    private String name;
    private String content;
    private Integer number;
    private String startTime;
    private String endTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer deleted;
}
