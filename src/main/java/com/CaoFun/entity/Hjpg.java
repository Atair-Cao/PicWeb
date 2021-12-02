package com.CaoFun.entity;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 1:10
 * @E-Mail newaccountc@163.com
 */
public class Hjpg {

    private Integer id;
    private String name;
    private String savedir;
    private String originurl;


    @Override
    public String toString() {
        return "Hjpg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", savedir='" + savedir + '\'' +
                ", originurl='" + originurl + '\'' +
                '}';
    }

    public String getOriginurl() {
        return originurl;
    }

    public void setOriginurl(String originurl) {
        this.originurl = originurl;
    }

    public String getSavedir() {
        return savedir;
    }

    public void setSavedir(String savedir) {
        this.savedir = savedir;
    }

    public Hjpg() {

    }

    public Hjpg(Integer id, String name,String savedir,String originurl) {
        this.id = id;
        this.name = name;
        this.savedir=savedir;
        this.originurl=originurl;
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
}
