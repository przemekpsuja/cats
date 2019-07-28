package com.sda.cats.service;

import com.sda.cats.model.Cat;
import com.sda.cats.model.CatResponse;
import com.sda.cats.model.CreateCatRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CatMapper {

    public CatResponse map(Cat cat) {
        return CatResponse.builder()
                .name(cat.getName())
                .age(cat.getAge())
                .hasTail(cat.hasTail())
                .build();
    }

    public static Cat map(CreateCatRequest cat) {
        return Cat.builder()
                .name(cat.getName())
                .age(cat.getAge())
                .hasTail(cat.isHasTail())
                .build();
    }
}
