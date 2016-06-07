package com.fitmeet.plank;

/**
 * Created by lvhonghe on 16/6/1.
 */
public class PlankData {
    private long timeStamp;
    private String des;
    private long timeCount;


    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public long getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(long timeCount) {
        this.timeCount = timeCount;
    }

    public PlankData(long timeStamp, long timeCount) {
        this.timeStamp = timeStamp;
        this.timeCount = timeCount;
    }
}
