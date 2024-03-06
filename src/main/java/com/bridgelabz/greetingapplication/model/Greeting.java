package com.bridgelabz.greetingapplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("greetings")
public class Greeting {
    @Id
    private int id;
    private String name;
    private String msg;

    public Greeting(int id, String name, String msg) {
        this.id = id;
        this.name = name;
        this.msg = msg;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
