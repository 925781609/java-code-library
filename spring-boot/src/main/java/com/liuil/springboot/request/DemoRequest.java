package com.liuil.springboot.request;

/**
 * Demo请求
 */
public class DemoRequest {
    private String parameter1;
    private String parameter2;

    public DemoRequest() {
    }

    public DemoRequest(String parameter1, String parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    @Override
    public String toString() {
        return "DemoRequest{" +
                "parameter1='" + parameter1 + '\'' +
                ", parameter2='" + parameter2 + '\'' +
                '}';
    }
}
