package com.example.springbootjmx.jmx;


import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@ManagedResource(
        objectName = "com.example.springbootjmx:type=SimpleBean",
        description = "这是一个简单的Bean，被Spring托管"
)

@Component
public class SimpleBean {

    private long id;

    private String name;


    private int value;

    @ManagedAttribute(description = "ID 属性")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManagedAttribute(description = "名称 属性")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @ManagedAttribute(description = "display 操作")
    public String display() {


        return this.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
