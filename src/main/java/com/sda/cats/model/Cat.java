package com.sda.cats.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@Entity(name = "cats")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Cat {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    @Accessors(fluent = true)
    private boolean hasTail;
    private LocalDate createdAt;

    @PrePersist
    private void preSave() {
        createdAt = LocalDate.now();
    }

}
