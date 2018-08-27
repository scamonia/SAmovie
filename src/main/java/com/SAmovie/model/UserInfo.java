package com.SAmovie.model;

/**
 * Created by scamonia on 2018/5/9.
 */
public class UserInfo {
    private String uid;
    private String gender;
    private String birthday;
    private String intro;

    public UserInfo(String uid, String gender, String birthday, String intro) {
        this.uid = uid;
        this.gender = gender;
        this.birthday = birthday;
        this.intro = intro;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
