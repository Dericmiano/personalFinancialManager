package com.example.personalfinancialmanager.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM d yyy");
        return simpleDateFormat.format(date);

    }
}
