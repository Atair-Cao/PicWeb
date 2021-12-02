package com.CaoFun.service.impl;

import com.CaoFun.dao.HlabelDao;
import com.CaoFun.dao.impl.HjpgDaoImpl;
import com.CaoFun.dao.impl.HlabelDaoImpl;
import com.CaoFun.entity.Hlabel;
import com.CaoFun.service.HlabelService;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 7:19
 * @E-Mail newaccountc@163.com
 */
public class HlabelServiceImpl implements HlabelService {

    private HlabelDao hlabelDao=new HlabelDaoImpl();

    @Override
    public void save(List<Hlabel> list) {
        hlabelDao.save(list);
    }
}
