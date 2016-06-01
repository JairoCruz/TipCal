package edu.jairo.android.tipcal.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TSE on 01/06/2016.
 */
public class TipRecord {

    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTip(){
        return bill * (tipPercentage / 100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMm dd,yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }
}