package com.CaoFun.dao;

import com.CaoFun.entity.*;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 1:23
 * @E-Mail newaccountc@163.com
 */
public interface HjpgDao {

    /**
     * 随机色图
     */
    public Hjpg queryRand();

    /**
     * 根据特定标签获得色图集合()
     */
    public List<Hjpg> queryByTag(String tag);

    /**
     * 保存色图信息
     */
    public void save (List<Hjpg> list);
}
