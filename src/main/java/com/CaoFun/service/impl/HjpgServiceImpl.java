package com.CaoFun.service.impl;

import com.CaoFun.dao.HjpgDao;
import com.CaoFun.dao.impl.HjpgDaoImpl;
import com.CaoFun.entity.Hjpg;
import com.CaoFun.service.HjpgService;

import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 2:15
 * @E-Mail newaccountc@163.com
 */
public class HjpgServiceImpl implements HjpgService {

    private HjpgDao hjpgDao=new HjpgDaoImpl();

    @Override
    public void save(List<Hjpg> list) {
        hjpgDao.save(list);
    }

    @Override
    public Hjpg getRandImage() {
        return hjpgDao.queryRand();
    }

    @Override
    public List<Hjpg> getImageByTag(String tag) {
        return null;
    }
}
