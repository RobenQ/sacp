package com.sacp;

import static org.junit.Assert.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        int weekYear = Calendar.getInstance().getWeekYear();
        String header =  String.valueOf(weekYear).substring(2);
        Random random = new Random();
        int a = random.nextInt(999);
        String mid = ""+String.format("%03d",a);
        System.out.println(header+mid+System.currentTimeMillis());
    }
}
