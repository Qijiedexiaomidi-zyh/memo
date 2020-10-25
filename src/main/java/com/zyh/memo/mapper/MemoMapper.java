package com.zyh.memo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zyh.memo.entity.Memo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @Author ZYH
 * @Date 2020/10/24 10:38
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Mapper
public interface MemoMapper extends BaseMapper<Memo> {

    int addMemo(@Param("memo") Memo memo, @Param("flag") int flag);

    IPage<Memo> listMemoByCondition(@Param("page") IPage<Memo> page, @Param("now") Date now);

    IPage<Memo> listDeadMemo(@Param("page") IPage<Memo> page, @Param("now") Date now);

    IPage<Memo> search(@Param("page") IPage<Memo> page, @Param("keyword") String keyword, @Param("now") Date now, @Param("level") Integer level);
}
