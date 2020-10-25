package com.zyh.memo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author ZYH
 * @Date 2020/10/24 10:34
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Data
@TableName("memo")
public class Memo {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private String content;
    private Integer level;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
}
