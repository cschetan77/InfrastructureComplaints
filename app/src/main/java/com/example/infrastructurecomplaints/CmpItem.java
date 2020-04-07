package com.example.infrastructurecomplaints;

import android.content.Intent;

import java.util.Comparator;

public class CmpItem implements Comparable<CmpItem>{
    public String Subject;
    public String Date;
    public String Time;

    public CmpItem(String subject,String date,String time) {
        this.Subject = subject;
        this.Date = date;
        this.Time = time;
    }

    @Override
    public int compareTo(CmpItem o) {
        return Date.compareTo(o.Date);
    }


}

