package com.example.spaceexplorer;

public class ItemData {
    public String itemTitle;
    public String itemDesc;
    public String itemImage;



    public ItemData() {
        // Constructor default
    }


    public void itemTitle(String title) {
        this.itemTitle = title;
    }

    public void itemDesc(String desc) {
        this.itemDesc =  desc;
    }

    public void itemImage(String image) {
        this.itemImage = image;
    }
}

