package com.SAmovie.model;

/**
 * Created by scamonia on 2018/5/4.
 */
public class MovieList {
    private int mlid;
    private String uid;
    private String mlname;
    private String midlist;
    private String mltype;
    private String creator;

    public  MovieList(){

    }
    public MovieList(String uid, String mlname, String midlist, String mltype, String creator) {
        this.uid = uid;
        this.mlname = mlname;
        this.midlist = midlist;
        this.mltype = mltype;
        this.creator = creator;
    }

    public MovieList(int mlid, String uid, String mlname, String mltype, String creator) {
        this.mlid = mlid;
        this.uid = uid;
        this.mlname = mlname;
        this.mltype = mltype;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "mlid=" + mlid +
                ", uid='" + uid + '\'' +
                ", mlname='" + mlname + '\'' +
                ", midlist='" + midlist + '\'' +
                ", mltype='" + mltype + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }

    public int getMlid() {
        return mlid;
    }

    public void setMlid(int mlid) {
        this.mlid = mlid;
    }

    public String getMlname() {
        return mlname;
    }

    public void setMlname(String mlname) {
        this.mlname = mlname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMidlist() {
        return midlist;
    }

    public void setMidlist(String midlist) {
        this.midlist = midlist;
    }

    public String getMltype() {
        return mltype;
    }

    public void setMltype(String mltype) {
        this.mltype = mltype;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
