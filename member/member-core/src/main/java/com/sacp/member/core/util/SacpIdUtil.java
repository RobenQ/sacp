package com.sacp.member.core.util;

import java.util.Calendar;
import java.util.Random;

public class SacpIdUtil {
    public static String generateSacpId(){
        int weekYear = Calendar.getInstance().getWeekYear();
        String header =  String.valueOf(weekYear).substring(2);
        Random random = new Random();
        int a = random.nextInt(999);
        String mid = ""+String.format("%03d",a);
         return header+mid+System.currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(generateSacpId());
    }
}
