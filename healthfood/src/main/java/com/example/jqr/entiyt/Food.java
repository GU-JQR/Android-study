package com.example.jqr.entiyt;

/**
 * 食品类  模版
 * 属性 （编号  标题  不宜通吃 图片资源  食品详情）
 */
public class Food {
    private Integer id;
    private String foodTitle;
    private String foodEat;
    private String picPath;
    private String foodDesc;

    public Food() {
    }

    public Food(Integer id, String foodTitle, String foodEat, String picPath, String foodDesc) {
        this.id = id;
        this.foodTitle = foodTitle;
        this.foodEat = foodEat;
        this.picPath = picPath;
        this.foodDesc = foodDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getFoodEat() {
        return foodEat;
    }

    public void setFoodEat(String foodEat) {
        this.foodEat = foodEat;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodTitle='" + foodTitle + '\'' +
                ", foodEat='" + foodEat + '\'' +
                ", picPath='" + picPath + '\'' +
                ", foodDesc='" + foodDesc + '\'' +
                '}';
    }
}
