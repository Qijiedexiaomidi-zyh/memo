package com.zyh.memo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyh.memo.entity.Memo;
import com.zyh.memo.mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author ZYH
 * @Date 2020/10/24 10:39
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    private MemoMapper memoMapper;

    @Override
    public IPage<Memo> listMemo(IPage<Memo> page,Date now) {
        return memoMapper.listMemoByCondition(page,now);
    }

    @Override
    public Memo findMemoById(Long id) {
        return memoMapper.selectById(id);
    }

    @Override
    public int addMemo(Memo memo,int flag) {
        //设定创建时间为当前时间
        memo.setCreateTime(new Date());
        //设定截止时间为flag天后
        memo.setDeadTime(getNextDay(new Date(),flag));
        return memoMapper.addMemo(memo,flag);
    }

    @Override
    public IPage<Memo> listDeadMemo(IPage<Memo> page, Date now) {
        return memoMapper.listDeadMemo(page,now);
    }

    @Override
    public IPage<Memo> search(IPage<Memo> page, String keyword,Date now,Integer level) {
        return memoMapper.search(page,keyword,now,level);
    }

    //获得flag天后的时间
    private Date getNextDay(Date date, int flag) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +flag);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }
}
