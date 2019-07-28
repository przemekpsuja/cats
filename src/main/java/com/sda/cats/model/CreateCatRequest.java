package com.sda.cats.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCatRequest {

    @NotNull
    private String name;
    @NotNull
    private int age;
    @NotNull
    private boolean hasTail;
}
