package com.zyh.memo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author ZYH
 * @Date 2020/10/24 11:02
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Data
@TableName("user")
public class User {

    private String username;
    private String password;
}
