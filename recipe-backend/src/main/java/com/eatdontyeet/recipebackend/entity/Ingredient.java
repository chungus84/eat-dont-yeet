package com.eatdontyeet.recipebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private Long id;
    private float amount;
    private String unit;
    private String unitLong;
    private String unitShort;
    private String aisle;
    private String name;
    private String original;
    private String originalName;
    private List<String> meta;
    private String image;

    @Override
    public String toString() {
        return "Ingredient{" +
                "id:" + id +
                ", amount:" + amount +
                ", unit:'" + unit + '\'' +
                ", unitLong:'" + unitLong + '\'' +
                ", unitShort:'" + unitShort + '\'' +
                ", aisle:'" + aisle + '\'' +
                ", name:'" + name + '\'' +
                ", original:'" + original + '\'' +
                ", originalName:'" + originalName + '\'' +
                ", meta:" + meta +
                ", image:'" + image + '\'' +
                '}';
    }
}
