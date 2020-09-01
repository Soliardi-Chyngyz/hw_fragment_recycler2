package com.example.hw_fragment_recycler;

import java.io.Serializable;

public class Title implements Serializable {
    public String name, surname, age;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
