package com.zyh.memo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyh.memo.entity.Memo;
import com.zyh.memo.service.MemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author ZYH
 * @Date 2020/10/24 10:43
 * @Blog https://qijiedexiaomidi-zyh.github.io
 * @Faith 干就完了，不多哔哔
 */
@Controller
@RequestMapping("/memo")
public class MemoController {

    private static final Logger logger = LoggerFactory.getLogger(MemoController.class);

    @Autowired
    private MemoService memoService;

    /**
     * 按照事项紧急程度的从大到小排序 分页查询所有 没有过期的 备忘录信息
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/listMemo")
    public String listMemo(@RequestParam(defaultValue = "1") Long pageNum, Model model){
        int pageSize = 3; //设置默认页面大小
        Page<Memo> page = new Page<>(pageNum,pageSize); //创建分页对象，并设定当前页码和页面大小

        Date now = new Date();
        IPage<Memo> memoByPage = memoService.listMemo(page,now);
        model.addAttribute("memoByPage",memoByPage);
        logger.info("查询出来的所有备忘录数据："+memoByPage.getRecords());
        return "listMemo";
    }

    /**
     * 根据id查询单个备忘录信息的展示
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findMemoById")
    public String findMemoById(Long id, Model model){
        Memo memo = memoService.findMemoById(id);
        if (memo==null){
            logger.info("单个备忘录查询失败");
        }else {
            logger.info("单个备忘录查询成功："+memo);
            model.addAttribute("memo",memo);
        }
        return "memoView";
    }

    /**
     * 去备忘录添加页面
     * @return
     */
    @RequestMapping("/gotoAddMemo")
    public String gotoAddMemo(){
        return "addMemo";
    }

    /**
     * 添加备忘录
     * @param memo
     * @return
     */
    @RequestMapping("/addMemo")
    public String addMemo(Memo memo,int flag){
        int row = memoService.addMemo(memo,flag);
        if (row>0){
            logger.info("增加备忘录成功："+memo);
        }else {
            logger.info("增加备忘录失败");
        }
        return "redirect:/memo/listMemo";
    }

    /**
     * 按照事项紧急程度的从大到小排序 分页查询所有 已经过期的 备忘录信息
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/listDeadMemo")
    public String listDeadMemo(@RequestParam(defaultValue = "1") Long pageNum, Model model){
        int pageSize = 3; //设置默认页面大小
        Page<Memo> page = new Page<>(pageNum,pageSize); //创建分页对象，并设定当前页码和页面大小

        Date now = new Date();
        IPage<Memo> memoByPage = memoService.listDeadMemo(page,now);
        model.addAttribute("memoByPage",memoByPage);
        logger.info("查询出来的所有已过期的备忘录数据："+memoByPage.getRecords());
        return "deadMemo";
    }

    /**
     * 根据输入框中的keyword值、紧急程度分页搜索没有过期的数据
     * @param pageNum
     * @param keyword
     * @param level
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "1") Long pageNum,String keyword,Integer level, Model model){
        int pageSize = 3; //设置默认页面大小
        Page<Memo> page = new Page<>(pageNum,pageSize); //创建分页对象，并设定当前页码和页面大小

        Date now = new Date();
        IPage<Memo> memoByPage = memoService.search(page,keyword,now,level);
        model.addAttribute("memoByPage",memoByPage);
        logger.info("查询出来的所有已过期的备忘录数据："+memoByPage.getRecords());
        return "listMemo";
    }

    /**
     * 待办事项的删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteMemo")
    public String deleteMemo(Long id){
        int row = memoService.deleteMemo(id);
        if (row > 0){
            logger.info("删除数据成功");
        }else {
            logger.info("删除数据失败");
        }
        return "redirect:/memo/listMemo";
    }
}
