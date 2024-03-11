package com.main.util;

import java.util.Base64;

public class PasswordEncoderUtil {

	public static String encoder(String password)
	{
		 String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
         return encodedPassword;
	}
	public static String decoder(String encodedPwd)
	{
		 
        byte[] decodedBytes = Base64.getDecoder().decode(encodedPwd);
        String decodedPassword = new String(decodedBytes);
        
        return decodedPassword;
	}
}
