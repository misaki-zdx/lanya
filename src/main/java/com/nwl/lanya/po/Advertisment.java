package com.nwl.lanya.po;

public class Advertisment {
    private String id;

    private String imgName;

    private String imgUrl;

    private String addIntro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getAddIntro() {
        return addIntro;
    }

    public void setAddIntro(String addIntro) {
        this.addIntro = addIntro == null ? null : addIntro.trim();
    }
}