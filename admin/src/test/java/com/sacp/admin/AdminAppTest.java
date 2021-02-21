package com.sacp.admin;

import static org.junit.Assert.assertTrue;

import com.sacp.member.client.util.SecurityUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
public class AdminAppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws NoSuchAlgorithmException {
        System.out.println(SecurityUtil.securityPassword("111111"));
    }
}
