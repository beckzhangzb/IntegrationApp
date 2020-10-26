package com.my;

/**
 * @author: zhangzb
 * @date: 2020/10/26 11:05
 * @description: 谈谈，在new User(1, "张三") 实例，在内存中是如何分配的
 */
public class User {
    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
