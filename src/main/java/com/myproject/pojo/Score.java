package com.myproject.pojo;

public class Score {
    private Integer sid;
    private Integer cid;
    private Float score;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "sid=" + sid +
                ", cid=" + cid +
                ", score=" + score +
                '}';
    }
}
