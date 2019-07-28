package com.sda.cats.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatResponse {

    private String name;
    private Integer age;
    private boolean hasTail;
}
