package com.SAmovie.model;

/**
 * Created by scamonia on 2018/4/18.
 */
public class BoxofficeDetail {

    private int seq;
    private String name;
    private String days;
    private String totals;
    private String realtime;
    private String boxofficeRatio;
    private String platoonRatio;
    private String seatRate;
    private String seatRatio;
    private String screenings;
    private String ticketNum;
    private String avgticketNum;
    private String avgIncome;
    private String avgticketPrice;

    @Override
    public String toString() {
        return "BoxofficeDetail{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", days='" + days + '\'' +
                ", totals='" + totals + '\'' +
                ", realtime='" + realtime + '\'' +
                ", boxofficeRatio='" + boxofficeRatio + '\'' +
                ", platoonRatio='" + platoonRatio + '\'' +
                ", seatRate='" + seatRate + '\'' +
                ", seatRatio='" + seatRatio + '\'' +
                ", screenings='" + screenings + '\'' +
                ", ticketNum='" + ticketNum + '\'' +
                ", avgticketNum='" + avgticketNum + '\'' +
                ", avgIncome='" + avgIncome + '\'' +
                ", avgticketPrice='" + avgticketPrice + '\'' +
                '}';
    }

    public String getAvgticketNum() {
        return avgticketNum;
    }

    public void setAvgticketNum(String avgticketNum) {
        this.avgticketNum = avgticketNum;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTotals() {
        return totals;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public String getBoxofficeRatio() {
        return boxofficeRatio;
    }

    public void setBoxofficeRatio(String boxofficeRatio) {
        this.boxofficeRatio = boxofficeRatio;
    }

    public String getPlatoonRatio() {
        return platoonRatio;
    }

    public void setPlatoonRatio(String platoonRatio) {
        this.platoonRatio = platoonRatio;
    }

    public String getSeatRate() {
        return seatRate;
    }

    public void setSeatRate(String seatRate) {
        this.seatRate = seatRate;
    }

    public String getSeatRatio() {
        return seatRatio;
    }

    public void setSeatRatio(String seatRatio) {
        this.seatRatio = seatRatio;
    }

    public String getScreenings() {
        return screenings;
    }

    public void setScreenings(String screenings) {
        this.screenings = screenings;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(String avgIncome) {
        this.avgIncome = avgIncome;
    }

    public String getAvgticketPrice() {
        return avgticketPrice;
    }

    public void setAvgticketPrice(String avgticketPrice) {
        this.avgticketPrice = avgticketPrice;
    }
}
