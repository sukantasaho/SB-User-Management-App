package com.main.util;

import java.util.Random;

public class PasswordGeneratorUtil 
{
	
	public static String generateRandomPassword()
	{
		  String upperAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerAlphabets = "abcdefghijklmnopqrstuvwxyz";
	      String numbers = "0123456789";
	      String alphaNumeric = upperAlphabets+lowerAlphabets+numbers;
	      StringBuilder sb = new StringBuilder();
	      Random random = new Random();
	      int length = 8;
	      for(int i = 0; i<length; i++)
	      {
	    	 int index = random.nextInt(alphaNumeric.length());
	    	 Character randomChar = alphaNumeric.charAt(index);
	    	 sb.append(randomChar);
	      }
	      return sb.toString();
	}
      
}
