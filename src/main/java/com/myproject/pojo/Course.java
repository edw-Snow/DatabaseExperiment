package com.myproject.pojo;

public class Course {
    private Integer cid;
    private String name;
    private Float credits;
    private Integer tid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCredits() {
        return credits;
    }

    public void setCredits(Float credits) {
        this.credits = credits;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", tid=" + tid +
                '}';
    }
}
