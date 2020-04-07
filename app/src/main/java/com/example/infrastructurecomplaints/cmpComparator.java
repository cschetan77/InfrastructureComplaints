package com.example.infrastructurecomplaints;

import java.util.Comparator;

public  class cmpComparator implements Comparator<CmpItem> {

    @Override
    public int compare(CmpItem o1, CmpItem o2) {
        //Comparing year
        int yo1 = Integer.parseInt(o1.Date.substring(6,10));
        int yo2 = Integer.parseInt(o2.Date.substring(6,10));
        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }

        //comparing month
        yo1 = Integer.parseInt(o1.Date.substring(3,5));
        yo2 = Integer.parseInt(o2.Date.substring(3,5));
        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }

        //Comaring date
        yo1 = Integer.parseInt(o1.Date.substring(0,2));
        yo2 = Integer.parseInt(o2.Date.substring(0,2));

        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }

        //Comparing Hours
        yo1 = Integer.parseInt(o1.Time.substring(0,2));
        yo2 = Integer.parseInt(o2.Time.substring(0,2));

        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }

        //Comparing minutes
        yo1 = Integer.parseInt(o1.Time.substring(3,5));
        yo2 = Integer.parseInt(o2.Time.substring(3,5));

        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }

        //Comparing seconds
        yo1 = Integer.parseInt(o1.Time.substring(6,8));
        yo2 = Integer.parseInt(o2.Time.substring(6,8));

        if(yo1 > yo2) {
            return -1;
        }
        else if(yo1 < yo2) {
            return 1;
        }


        //Equal Objects
        return 0;
    }
}