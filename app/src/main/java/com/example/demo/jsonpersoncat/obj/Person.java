package com.example.demo.jsonpersoncat.obj;

import java.util.Arrays;

public class Person {
    private String name;
    private int age;
    private String[] hobbies;
    private Address address;

    public Person(String name, int age, String[] hobbies, Address address) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", address=" + address +
                '}';
    }
}
