package com.SAmovie.model;

/**
 * Created by scamonia on 2018/5/17.
 */
public class SerREC {
    private String uid;
    private String chtype;
    private String entype;
    private int typeval;
    private int allval;

    @Override
    public String toString() {
        return "SerREC{" +
                "uid='" + uid + '\'' +
                ", chtype='" + chtype + '\'' +
                ", entype='" + entype + '\'' +
                ", typeval='" + typeval + '\'' +
                ", allval='" + allval + '\'' +
                '}';
    }

    public SerREC(String uid, String chtype, String entype, int typeval, int allval) {
        this.uid = uid;
        this.chtype = chtype;
        this.entype = entype;
        this.typeval = typeval;
        this.allval = allval;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getChtype() {
        return chtype;
    }

    public void setChtype(String chtype) {
        this.chtype = chtype;
    }

    public String getEntype() {
        return entype;
    }

    public void setEntype(String entype) {
        this.entype = entype;
    }

    public int getTypeval() {
        return typeval;
    }

    public void setTypeval(int typeval) {
        this.typeval = typeval;
    }

    public int getAllval() {
        return allval;
    }

    public void setAllval(int allval) {
        this.allval = allval;
    }
}
