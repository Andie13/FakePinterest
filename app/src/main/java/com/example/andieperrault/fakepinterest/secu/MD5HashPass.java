package com.example.andieperrault.fakepinterest.secu;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andieperrault on 14/12/2018.
 */

public class MD5HashPass {



    public String getHashedPass(String passToHash){
         String hashedPass = md5(passToHash);
         Log.i("HASH",hashedPass);
         return hashedPass;

    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

        //HEXAString a la tendence à cropper le 1er 0 du hash, il faut donc le rajouter à la main.
            return "0"+hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
