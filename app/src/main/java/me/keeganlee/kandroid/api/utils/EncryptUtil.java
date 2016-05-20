package me.keeganlee.kandroid.api.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * Created by Tian on 2016/5/3.
 */
public class EncryptUtil {

    public static String makeMD5(String password) {
        try {
            MessageDigest md = null;
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;

    }
}
