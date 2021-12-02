package com.CaoFun.entity;

/**
 * @Author Jiabo.Cao
 * @Date 2021/11/27 4:27
 * @E-Mail newaccountc@163.com
 */
public class Hlabel {

    private Integer id;
    private String image;//文件名称
    private String tag;//标签名称

    public String getEasycode() {
        return easycode;
    }

    public void setEasycode(String easycode) {
        this.easycode = easycode;
    }

    private String easycode;//唯一标签

    @Override
    public String toString() {
        return "Hlabel{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Hlabel() {
    }

    public Hlabel(String image,String tag){
        this.image=image;
        this.tag=tag;
    }

}
