package com.CaoFun.dao;

import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.Htag;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:34
 * @E-Mail newaccountc@163.com
 */
public interface HtagDao {
    /**
     * 获得最近所有热门标签
     */
    public List<Htag> HotpotTags();

    /**
     * 简单获取标签名称对应的标签
     */
    public List<Htag> SearchTags(List<String> tagname);


    /**
     * 保存标签信息
     */
    public void save (List<Htag> list);
}
