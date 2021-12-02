package com.CaoFun.dao.impl;

import com.CaoFun.dao.HjpgDao;
import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 1:48
 * @E-Mail newaccountc@163.com
 */
public class HjpgDaoImpl extends BaseDao implements HjpgDao {
    @Override
    public Hjpg queryRand() {
        String sql = "select `id`,`name`,`savedir`,`originurl` from t_image order by rand() limit 1";
        return queryRandomOne(Hjpg.class, sql);
    }

    @Override
    public List<Hjpg> queryByTag(String tag) {
        return null;
    }

    @Override
    public void save(List<Hjpg> list) {
        String sql = "insert ignore into t_image(`name`,`savedir`,`originurl`) values(?,?,?)";//加上ignore保证如果有重复的图片就不再添加了
        List<Object[]> arglist=new ArrayList<Object[]>();
        for(Hjpg hjpg:list){
            String[] strArray={hjpg.getName(),hjpg.getSavedir(),hjpg.getOriginurl()};
            arglist.add(strArray);
        }
        MultiInsert(sql, arglist);
    }
}
