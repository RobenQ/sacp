package com.sacp.web.util;

import com.qiniu.util.Auth;

public class QiNiuUploadUtil {
    private static final String ACCESS_KEY = "7g5N18_AR4lq3vi94Ec-Pd9SWRAuJuH5n_KeZm77";
    private static final String SECRET_KEY = "1Shg_XGTDm3sKd0vAy8q_tJ2ZFQ6ZuaPiBL9yN_O";
    private static final String BUCKET = "sacp-avatar";
//    String key = "file key";

    private static String getToken(String accessKey,String secretKey,String bucket){
        Auth auth = Auth.create(accessKey,secretKey);
        return auth.uploadToken(bucket);
    }

    public static String getDefaultToken(){
        return getToken(ACCESS_KEY,SECRET_KEY,BUCKET);
    }

}
