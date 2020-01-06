package com.bin.bean;

/**
 * @program: javautils
 * @description:
 * @author: jyb
 * @create: 2019-09-09 11:05
 **/
public class Person {
    private String name;
    private String age;
    private String num;
    private String date;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", num='" + num + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
