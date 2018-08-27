package com.SAmovie.model;

/**
 * Created by scamonia on 2018/5/3.
 */
public class Movie {
    private int mid;
    private String name;
    private String tag;
    private String poster;
    private String director;
    private String screenwriter;
    private String performer;
    private String country;
    private float score;
    private int scoreitem;
    private int commentitem;
    private int year;
    private String details;

    @Override
    public String toString() {
        return "Movie{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", poster='" + poster + '\'' +
                ", director='" + director + '\'' +
                ", screenwriter='" + screenwriter + '\'' +
                ", performer='" + performer + '\'' +
                ", country='" + country + '\'' +
                ", score=" + score +
                ", scoreitem=" + scoreitem +
                ", commentitem=" + commentitem +
                ", year=" + year +
                ", details='" + details + '\'' +
                '}';
    }

    public Movie() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getScoreitem() {
        return scoreitem;
    }

    public void setScoreitem(int scoreitem) {
        this.scoreitem = scoreitem;
    }

    public int getCommentitem() {
        return commentitem;
    }

    public void setCommentitem(int commentitem) {
        this.commentitem = commentitem;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
