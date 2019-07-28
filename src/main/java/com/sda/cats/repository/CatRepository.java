package com.sda.cats.repository;

import com.sda.cats.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {

}
