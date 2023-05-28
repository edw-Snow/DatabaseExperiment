package com.myproject.pojo;

import java.text.DecimalFormat;

public class TeacherStat {
    private Integer tid;
    private String name;
    private String cname;
    private Float avgScore;
    private Float maxScore;
    private Float minScore;
    static DecimalFormat df = new DecimalFormat("#0.00");

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Float getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Float avgScore) {
        this.avgScore = Float.valueOf(df.format(avgScore));
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore=Float.valueOf(df.format(maxScore));
    }

    public Float getMinScore() {
        return minScore;
    }

    public void setMinScore(Float minScore) {
        this.minScore=Float.valueOf(df.format(minScore));
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "TeacherStat{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", cname='" + cname + '\'' +
                ", avgScore=" + avgScore +
                ", maxScore=" + maxScore +
                ", minScore=" + minScore +
                '}';
    }
}
