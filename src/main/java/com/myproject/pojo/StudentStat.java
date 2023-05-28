package com.myproject.pojo;

public class StudentStat {
    private Integer id;
    private String sname;
    private Integer cid;
    private String cname;
    private Float score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentStat{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", cid=" + cid +
                ", cname='" + cname + '\'' +
                ", score=" + score +
                '}';
    }
}
