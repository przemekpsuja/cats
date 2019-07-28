package com.sda.cats.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatchCatRequest {

    private String name;
    private Integer age;
    private Boolean hasTail;

}
