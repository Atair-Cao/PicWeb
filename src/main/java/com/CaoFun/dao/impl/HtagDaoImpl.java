package com.CaoFun.dao.impl;

import com.CaoFun.dao.HtagDao;
import com.CaoFun.entity.Hjpg;
import com.CaoFun.entity.Htag;
import com.CaoFun.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:44
 * @E-Mail newaccountc@163.com
 */
public class HtagDaoImpl extends BaseDao implements HtagDao {

    @Override
    public List<Htag> HotpotTags() {
        String sql = "select `id`,`name`,`rate` from t_tag where t_tag.rate>100 order by t_tag.rate desc limit 20";
        return queryForList(Htag.class,sql);
    }

    @Override
    public List<Htag> SearchTags(List<String> tagname) {//利用建立临时表和数据库交集并集的操作快速得到tag集合的编号
        List<String> sqls=new ArrayList<String>();
        List<Object[]> args=new ArrayList<Object[]>();

        /** 第一步！！打开临时表*/
        sqls.add("CREATE TEMPORARY TABLE `temp` (`tp` VARCHAR(50))");
//        sqls.add("CREATE TABLE `temp` (`tp` VARCHAR(50))");
        args.add(new String[]{});
        /** 第二步！！建立临时表*/
        for(String to_add:tagname){
            sqls.add("insert into temp(`tp`) values(?)");
            args.add(new String[]{to_add});
        }
        /** 第三步！！查询*/
        sqls.add("select `id`,`name`,`rate` from t_tag, temp where t_tag.name=temp.tp");//打开临时表
        args.add(new String[]{});


        /** 第四步！！弃用临时表*/
        sqls.add("DROP TABLE sql_store.temp");//关闭临时表
        args.add(new String[]{});

        return Multiupdate(Htag.class, sqls, args);
    }

    @Override
    public void save(List<Htag> list) {
        String sql = "insert ignore into t_tag(`name`) values(?)";//加上ignore保证如果有重复的就不再添加了
        List<Object[]> arglist=new ArrayList<Object[]>();
        for(Htag htag:list){
            String[] strArray={htag.getName()};//因为新标签的初始热度是0，就是rate列指定了默认值，不用传参
            arglist.add(strArray);
        }
        MultiInsert(sql, arglist);
    }
}
