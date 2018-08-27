package com.SAmovie.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by scamonia on 2018/5/7.
 */
public class Comment {
    int mid;
    String uid;
    String username;
    String content;
    Date cmtime;
    Timestamp getime;
    String pagetime;
    String name;
    int cmtid;

    public Comment(){

    }

    public Comment(int cmtid,int mid, String name, String content, Date cmtime) {
        this.cmtid=cmtid;
        this.mid = mid;
        this.name = name;
        this.content = content;
        this.cmtime = cmtime;
    }

    public Comment(int mid, String uid, String username, String content, Date cmtime) {
        this.mid = mid;
        this.uid = uid;
        this.username = username;
        this.content = content;
        this.cmtime = cmtime;
    }

    public Comment(int mid, String uid, String username, String content, Timestamp getime) {
        this.mid = mid;
        this.uid = uid;
        this.username = username;
        this.content = content;
        this.getime = getime;
    }


    public int getCmtid() {
        return cmtid;
    }

    public void setCmtid(int cmtid) {
        this.cmtid = cmtid;
    }

    public String getMname() {
        return name;

    }

    public void setMname(String mname) {
        this.name = mname;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCmtime() {
        return cmtime;
    }

    public void setCmtime(Date cmtime) {
        this.cmtime = cmtime;
    }

    public Timestamp getGetime() {
        return getime;
    }

    public void setGetime(Timestamp getime) {
        this.getime = getime;
    }

    public String getPagetime() {
        return pagetime;
    }

    public void setPagetime(String pagetime) {
        this.pagetime = pagetime;
    }
}
