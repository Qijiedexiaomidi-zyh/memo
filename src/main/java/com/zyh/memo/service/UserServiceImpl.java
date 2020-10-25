package com.zyh.memo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyh.memo.entity.User;
import com.zyh.memo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ZYH
 * @Date 2020/10/24 11:05
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername()).eq("password",user.getPassword());
        return userMapper.selectOne(wrapper);
    }
}
