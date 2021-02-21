package com.sacp.member.client.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String securityPassword(String password) throws NoSuchAlgorithmException {
        if (password==null)
            throw new NullPointerException("密码为null");
        MessageDigest mDigest = MessageDigest.getInstance("SHA");
        mDigest.update(password.getBytes());
        String key = new BigInteger(mDigest.digest()).toString();
        return key;
    }
}
