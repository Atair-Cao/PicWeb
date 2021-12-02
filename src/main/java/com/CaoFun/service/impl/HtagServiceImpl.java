package com.CaoFun.service.impl;

import com.CaoFun.dao.HtagDao;
import com.CaoFun.dao.impl.HtagDaoImpl;
import com.CaoFun.entity.Htag;
import com.CaoFun.service.HtagService;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:59
 * @E-Mail newaccountc@163.com
 */
public class HtagServiceImpl implements HtagService {

    private HtagDao htagDao=new HtagDaoImpl();

    @Override
    public void save(List<Htag> list) {
        htagDao.save(list);
    }

    @Override
    public List<Htag> WhatsUp() {
        return htagDao.HotpotTags();
    }

    @Override
    public List<Htag> Locate(List<String> tagname) {
        return htagDao.SearchTags(tagname);
    }
}
