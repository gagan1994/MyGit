package com.example.gagan.cloths.pojo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gagan on 5/3/2018.
 */

public class Cloths {
    private String imageUrl;
    private int id;
    private String type;
    private String size;
    private int cost;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public static Cloths getRandom() {
        Random random = new Random();

        Cloths c = new Cloths();
        c.id = random.nextInt();
        c.cost = random.nextInt();
        switch (random.nextInt() % 3) {
            case 0:
                c.size = "S";
                break;
            case 1:
                c.size = "M";
                break;
            case 2:
                c.size = "L";
                break;
        }
        switch (c.cost % 3) {
            case 0:
                c.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Blue_Tshirt.jpg/220px-Blue_Tshirt.jpg";
                c.type = "T-shirt Blue";

                break;
            case 1:
                c.imageUrl = "https://static.cilory.com/208999-thickbox_default/no-logo-white-navy-round-neck-t-shirt.jpg";
                c.type = "T-shirt round neck";

                break;
            default:
                c.imageUrl = "https://static4.cilory.com/238653-thickbox_default/black-superman-t-shirt.jpg";
                c.type = "T-shirt superman";
        }
        return c;
    }

    public ArrayList<String> getImages() {
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("https://static4.cilory.com/238653-thickbox_default/black-superman-t-shirt.jpg");
        ret.add("https://static.cilory.com/208999-thickbox_default/no-logo-white-navy-round-neck-t-shirt.jpg");
        ret.add("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Blue_Tshirt.jpg/220px-Blue_Tshirt.jpg");


        return ret;
    }
}
