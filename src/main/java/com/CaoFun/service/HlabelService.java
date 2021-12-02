package com.CaoFun.service;

import com.CaoFun.entity.Hlabel;
import com.CaoFun.entity.Htag;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 7:17
 * @E-Mail newaccountc@163.com
 */
public interface HlabelService {

    /**
     * 储存关联信息
     */
    public void save(List<Hlabel> list);


}
