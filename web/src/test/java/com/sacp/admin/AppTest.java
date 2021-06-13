package com.sacp.admin;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String address = "国家|大区|省份|城市|运营商";
        String[] addressList = address.split("\\|");
        for (String str:addressList
             ) {
            System.out.println(str);
        }
    }
}
