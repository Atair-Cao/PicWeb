package com.CaoFun.dao.impl;

import com.CaoFun.dao.HlabelDao;
import com.CaoFun.entity.Hlabel;
import com.CaoFun.entity.Htag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 6:45
 * @E-Mail newaccountc@163.com
 */
public class HlabelDaoImpl extends BaseDao implements HlabelDao {
    @Override
    public void save(List<Hlabel> list) {
        List<String> tagnames=new ArrayList<String>();
        for(Hlabel hlabel:list){
            tagnames.add(hlabel.getTag());
        }
        tagnames=tagnames.stream().distinct().collect(Collectors.toList());

        List<Htag> htags=new HtagDaoImpl().SearchTags(tagnames);
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for(Htag htag:htags){
            map.put(htag.getName(),htag.getId());
        }

        String sql = "insert ignore into t_label(`image`,`tag`,`easycode`) values(?,?,?)";//加上ignore保证如果有重复的就不再添加了
        List<Object[]> arglist=new ArrayList<Object[]>();
        for(Hlabel hlabel:list){
            String[] strArray={hlabel.getImage(),hlabel.getTag(),hlabel.getImage()+map.get(hlabel.getTag()).toString()};
            arglist.add(strArray);
        }
        MultiInsert(sql, arglist);
    }
}
