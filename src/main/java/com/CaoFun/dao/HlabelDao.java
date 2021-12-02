package com.CaoFun.dao;

import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.Hlabel;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 5:01
 * @E-Mail newaccountc@163.com
 */
public interface HlabelDao {

    /**
     * 保存图片—标签关联信息
     */
    public void save (List<Hlabel> list);
}
