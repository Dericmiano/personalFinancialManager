package com.example.personalfinancialmanager.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "spend_table")
public class Spend {
    @ColumnInfo(name = "spend_id")
    @PrimaryKey(autoGenerate = true)
    public long spendId;
    public Date dateToday;
    public int target;
    public int food;
    public int fee;
    public int gf;
    public int charity;
    public int airtime;
    public int otherNeed;
    public int entertainment;
    public int total;
    public int usage;
    public int save;

    public Spend(Date dateToday, int target, int food, int fee, int gf,
                 int charity, int airtime, int otherNeed, int entertainment) {
        this.dateToday = dateToday;
        this.target = target;
        this.food = food;
        this.fee = fee;
        this.gf = gf;
        this.charity = charity;
        this.airtime = airtime;
        this.otherNeed = otherNeed;
        this.entertainment = entertainment;
    }

//    public Spend(Date dateToday, int target, int food, int fee,
//                 int gf, int charity, int airtime, int otherNeed,
//                 int entertainment, int total, int usage, int save) {
//        this.dateToday = dateToday;
//        this.target = target;
//        this.food = food;
//        this.fee = fee;
//        this.gf = gf;
//        this.charity = charity;
//        this.airtime = airtime;
//        this.otherNeed = otherNeed;
//        this.entertainment = entertainment;
//        this.total = total;
//        this.usage = usage;
//        this.save = save;
//    }

    public long getSpendId() {
        return spendId;
    }

    public void setSpendId(long spendId) {
        this.spendId = spendId;
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getCharity() {
        return charity;
    }

    public void setCharity(int charity) {
        this.charity = charity;
    }

    public int getAirtime() {
        return airtime;
    }

    public void setAirtime(int airtime) {
        this.airtime = airtime;
    }

    public int getOtherNeed() {
        return otherNeed;
    }

    public void setOtherNeed(int otherNeed) {
        this.otherNeed = otherNeed;
    }

    public int getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    @Override
    public String toString() {
        return "Spend{" +
                "spendId=" + spendId +
                ", dateToday=" + dateToday +
                ", target=" + target +
                ", food=" + food +
                ", fee=" + fee +
                ", gf=" + gf +
                ", charity=" + charity +
                ", airtime=" + airtime +
                ", otherNeed=" + otherNeed +
                ", entertainment=" + entertainment +
                ", total=" + total +
                ", usage=" + usage +
                ", save=" + save +
                '}';
    }
}
