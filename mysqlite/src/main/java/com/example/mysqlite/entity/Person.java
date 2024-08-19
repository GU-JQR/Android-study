package com.example.mysqlite.entity;

/**
 * 学生对象
 * 属性：需要和table表里面的字段对应
 * _id  name
 */
public class Person {
    private Integer _id;
    private String name;
//    构造方法

    public Person() {
    }

    public Person(Integer _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }
}
