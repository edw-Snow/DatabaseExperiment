package com.myproject.pojo;

public class PositionStat {
    private String position;
    private Integer numberOfPosition;
    private Float avgSalaryOfPosition;

    public Integer getNumberOfPosition() {
        return numberOfPosition;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumberOfPosition(Integer numberOfPosition) {
        this.numberOfPosition = numberOfPosition;
    }


    public Float getAvgSalaryOfPosition() {
        return avgSalaryOfPosition;
    }

    public void setAvgSalaryOfPosition(Float avgSalaryOfPosition) {
        this.avgSalaryOfPosition = avgSalaryOfPosition;
    }

    @Override
    public String toString() {
        return "PositionStat{" +
                "position='" + position + '\'' +
                ", numberOfPosition=" + numberOfPosition +
                ", avgSalaryOfPosition=" + avgSalaryOfPosition +
                '}';
    }
}
