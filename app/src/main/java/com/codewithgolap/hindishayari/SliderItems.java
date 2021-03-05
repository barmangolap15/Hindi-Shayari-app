package com.codewithgolap.hindishayari;

public class SliderItems {

    //set to String, if you want to add image url from internet, but here i use static image from drawable
    private int image;

    //constructor

    public SliderItems(int image) {
        this.image = image;
    }

    //getter

    public int getImage() {
        return image;
    }
}
