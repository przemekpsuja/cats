package com.sda.cats.model;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCatRequest {


    private String name;
    private Integer age;
    @NotNull
    private boolean hasTail;



}

