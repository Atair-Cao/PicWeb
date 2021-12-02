package com.CaoFun.service;

import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.User;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 2:12
 * @E-Mail newaccountc@163.com
 */
public interface HjpgService {
    /**
     * 储存色图信息
     */
    public void save(List<Hjpg> list);

    /**
     * 获得随机色图
     */
    public Hjpg getRandImage();

    /**
     * 根据标签搜索色图
     */
    public List<Hjpg> getImageByTag(String tag);
}
