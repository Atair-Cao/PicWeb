package com.CaoFun.service;

import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.Htag;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:57
 * @E-Mail newaccountc@163.com
 */
public interface HtagService {
    /**
     * 储存标签
     */
    public void save(List<Htag> list);

    /**
     * 获得实时热点
     */
    public List<Htag> WhatsUp();

    /**
     * 获得对应名字的标签信息
     */
    public List<Htag> Locate(List<String> tagname);

}
