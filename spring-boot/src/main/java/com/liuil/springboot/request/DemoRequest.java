package com.liuil.springboot.request;

/**
 * Demo请求
 */
public class DemoRequest {
    /**
     * 参数1
     */
    private String parameter1;
    /**
     * 颜色
     */
    private Color color;

    public DemoRequest() {
    }

    public DemoRequest(String parameter1, Color color) {
        this.parameter1 = parameter1;
        this.color = color;
    }

    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
