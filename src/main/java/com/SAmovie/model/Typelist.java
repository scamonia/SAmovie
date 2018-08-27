package com.SAmovie.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by scamonia on 2018/4/23.
 */
public class Typelist {
    /*电影和用户 喜好类型*/
    private String typeid;
    private int plot = 0;
    private int comedy = 0;
    private int action = 0;
    private int love = 0;
    private int adventure = 0;
    private int animation = 0;
    private int crime = 0;
    private int documentary = 0;
    private int family = 0;
    private int fantasy = 0;
    private int history = 0;
    private int horrible = 0;
    private int science = 0;
    private int song = 0;
    private int suspense = 0;
    private int youth = 0;

    @Override
    public String toString() {
        return "Typelist{" +
                "id='" + typeid + '\'' +
                ", plot=" + plot +
                ", comedy=" + comedy +
                ", action=" + action +
                ", love=" + love +
                ", adventure=" + adventure +
                ", animation=" + animation +
                ", crime=" + crime +
                ", documentary=" + documentary +
                ", family=" + family +
                ", fantasy=" + fantasy +
                ", history=" + history +
                ", horrible=" + horrible +
                ", science=" + science +
                ", song=" + song +
                ", suspense=" + suspense +
                ", youth=" + youth +
                '}';
    }

    public void resetvalue(String[] selectlist){
        for (int i = 0;i<selectlist.length;i++){
            if(selectlist[i].equals( "plot")){
                this.plot = 24;
            }
            if(selectlist[i].equals("comedy")){
                this.comedy = 24;
            }
            if(selectlist[i].equals("action")){
                this.action = 24;
            }
            if(selectlist[i].equals("love")){
                this.love = 24;
            }
            if(selectlist[i].equals("adventure")){
                this.adventure = 24;
            }
            if(selectlist[i].equals("animation")){
                this.animation = 24;
            }
            if(selectlist[i].equals("crime")){
                this.crime = 24;
            }
            if(selectlist[i].equals("documentary")){
                this.documentary = 24;
            }
            if(selectlist[i].equals("family")){
                this.family = 24;
            }
            if(selectlist[i].equals("fantasy")){
                this.fantasy = 24;
            }
            if(selectlist[i].equals("history")){
                this.history = 24;
            }
            if(selectlist[i].equals("horrible")){
                this.horrible = 24;
            }
            if(selectlist[i].equals("science")){
                this.science = 24;
            }
            if(selectlist[i].equals("song")){
                this.song = 24;
            }
            if(selectlist[i].equals("suspense")){
                this.suspense = 24;
            }
            if(selectlist[i].equals("youth")){
                this.youth = 24;
            }
        }
    }

    public void useractvalue(String[] selectlist,int changvalue){
        for (int i = 0;i<selectlist.length;i++){
            if(selectlist[i].equals( "剧情")){
                this.plot = changvalue;
            }
            if(selectlist[i].equals("喜剧")){
                this.comedy = changvalue;
            }
            if(selectlist[i].equals("动作")){
                this.action = changvalue;
            }
            if(selectlist[i].equals("爱情")){
                this.love = changvalue;
            }
            if(selectlist[i].equals("冒险")){
                this.adventure = changvalue;
            }
            if(selectlist[i].equals("动画")){
                this.animation = changvalue;
            }
            if(selectlist[i].equals("犯罪")){
                this.crime = changvalue;
            }
            if(selectlist[i].equals("纪实")){
                this.documentary = changvalue;
            }
            if(selectlist[i].equals("家庭")){
                this.family = changvalue;
            }
            if(selectlist[i].equals("奇幻")){
                this.fantasy = changvalue;
            }
            if(selectlist[i].equals("历史")){
                this.history = changvalue;
            }
            if(selectlist[i].equals("恐怖")){
                this.horrible = changvalue;
            }
            if(selectlist[i].equals("科幻")){
                this.science = changvalue;
            }
            if(selectlist[i].equals("歌舞")){
                this.song = changvalue;
            }
            if(selectlist[i].equals("悬疑")){
                this.suspense = changvalue;
            }
            if(selectlist[i].equals("青春")){
                this.youth = changvalue;
            }
        }
    }

    /*计算总值*/
    public int Sumvalue(){
        int sumvalue = plot + comedy + action + love + adventure + animation + crime + documentary + family + fantasy + history + horrible + science + song + suspense + youth;
        return  sumvalue;
    }

    /*计算分类项*/
    public List<SerREC> RECtype(){
        List<SerREC> serRECs = new ArrayList<SerREC>();
        int sumval = Sumvalue();
        SerREC serREC1 = new SerREC(getId(),"剧情","plot",plot,sumval);
        SerREC serREC2 = new SerREC(getId(),"喜剧","comedy",comedy,sumval);
        SerREC serREC3 = new SerREC(getId(),"动作","action",action,sumval);
        SerREC serREC4 = new SerREC(getId(),"爱情","love",love,sumval);
        SerREC serREC5 = new SerREC(getId(),"冒险","adventure",adventure,sumval);
        SerREC serREC6 = new SerREC(getId(),"动画","animation",animation,sumval);
        SerREC serREC7 = new SerREC(getId(),"犯罪","crime",crime,sumval);
        SerREC serREC8 = new SerREC(getId(),"纪实","documentary",documentary,sumval);
        SerREC serREC9 = new SerREC(getId(),"家庭","family",family,sumval);
        SerREC serREC10 = new SerREC(getId(),"奇幻","fantasy",fantasy,sumval);
        SerREC serREC11 = new SerREC(getId(),"历史","history",history,sumval);
        SerREC serREC12 = new SerREC(getId(),"恐怖","horrible",horrible,sumval);
        SerREC serREC13 = new SerREC(getId(),"科幻","science",science,sumval);
        SerREC serREC14 = new SerREC(getId(),"歌舞","song",song,sumval);
        SerREC serREC15 = new SerREC(getId(),"悬疑","suspense",suspense,sumval);
        SerREC serREC16 = new SerREC(getId(),"青春","youth",youth,sumval);
        serRECs.add(serREC1);
        serRECs.add(serREC2);
        serRECs.add(serREC3);
        serRECs.add(serREC4);
        serRECs.add(serREC5);
        serRECs.add(serREC6);
        serRECs.add(serREC7);
        serRECs.add(serREC8);
        serRECs.add(serREC9);
        serRECs.add(serREC10);
        serRECs.add(serREC11);
        serRECs.add(serREC12);
        serRECs.add(serREC13);
        serRECs.add(serREC14);
        serRECs.add(serREC15);
        serRECs.add(serREC16);
        return  serRECs;
    }
    public String getId() {
        return typeid;
    }

    public void setId(String id) {
        this.typeid = id;
    }

    public int getPlot() {
        return plot;
    }

    public void setPlot(int plot) {
        this.plot = plot;
    }

    public int getComedy() {
        return comedy;
    }

    public void setComedy(int comedy) {
        this.comedy = comedy;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getAdventure() {
        return adventure;
    }

    public void setAdventure(int adventure) {
        this.adventure = adventure;
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
    }

    public int getCrime() {
        return crime;
    }

    public void setCrime(int crime) {
        this.crime = crime;
    }

    public int getDocumentary() {
        return documentary;
    }

    public void setDocumentary(int documentary) {
        this.documentary = documentary;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getFantasy() {
        return fantasy;
    }

    public void setFantasy(int fantasy) {
        this.fantasy = fantasy;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getHorrible() {
        return horrible;
    }

    public void setHorrible(int horrible) {
        this.horrible = horrible;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public int getSuspense() {
        return suspense;
    }

    public void setSuspense(int suspense) {
        this.suspense = suspense;
    }

    public int getYouth() {
        return youth;
    }

    public void setYouth(int youth) {
        this.youth = youth;
    }
}
