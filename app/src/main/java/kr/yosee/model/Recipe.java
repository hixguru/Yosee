package kr.yosee.model;

/**
 * Created by hwanik on 2017. 1. 27..
 */

public class Recipe {
    private String imgUrl;
    private String title;
    private String description;
    private String objectId;

    public Recipe(String imgUrl, String title, String description, String objectId) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.description = description;
        this.objectId = objectId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
