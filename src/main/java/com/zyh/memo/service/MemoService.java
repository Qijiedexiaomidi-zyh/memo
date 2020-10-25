package com.zyh.memo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.memo.entity.Memo;

import java.util.Date;

/**
 * @Author ZYH
 * @Date 2020/10/24 10:39
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
public interface MemoService {
    IPage<Memo> listMemo(IPage<Memo> page, Date now);

    Memo findMemoById(Long id);

    int addMemo(Memo memo,int flag);

    IPage<Memo> listDeadMemo(IPage<Memo> page, Date now);

    IPage<Memo> search(IPage<Memo> page, String keyword,Date now,Integer level);

    int deleteMemo(Long id);
}
