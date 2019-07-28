package com.sda.cats.exeption;

public class CatNotFoundExeption extends RuntimeException {

    public CatNotFoundExeption() {
        super("Cat not found");
    }
}
