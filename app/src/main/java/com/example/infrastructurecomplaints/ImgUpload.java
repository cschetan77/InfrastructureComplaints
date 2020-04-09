package com.example.infrastructurecomplaints;

public class ImgUpload {
    private String imgName;
    private String imgUrl;

    public ImgUpload() {

        //Empty constructor
    }
    public ImgUpload(String name,String url) {

        if(name.trim().equals("")) {
            name = "No Name";
        }
        this.imgName = name;
        this.imgUrl = url;
    }

    public String getImgName() {
        return imgName;
    }
    public void setImgName(String name) {
        imgName = name;
    }
    public String getImgUrl() {
        return imgUrl;
    }
    public  void setImgUrl(String url) {
        this.imgUrl = url;
    }
}
