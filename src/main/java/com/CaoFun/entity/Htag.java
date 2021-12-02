package com.CaoFun.entity;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:26
 * @E-Mail newaccountc@163.com
 */
public class Htag {

    private Integer id;
    private String name;//英文名
    private Integer rate;//受欢迎程度(访问频率)

    public Htag(){
        this.rate=0;
    }

    public Htag(String name){
        this.name=name;
        this.rate=0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer rate() {
        return rate;
    }

    public void setCode(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Htag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
