package com.SAmovie.model;

import java.util.Arrays;

/**
 * Created by scamonia on 2018/4/23.
 */
public class User {
    private String id;
    private String username;
    private String pwd;
    private String[] movietype;

    public User(String id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public User(String id, String username, String pwd, String[] movietype) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.movietype = movietype;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", movietype=" + Arrays.toString(movietype) +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String[] getMovietype() {
        return movietype;
    }

    public void setMovietype(String[] movietype) {
        this.movietype = movietype;
    }
}
