package com.main.util;
import java.util.Random;

public class PasswordGeneratorUtil 
{
	private static Random random = null;
	private static  Random getRandom()
	{
		if(random == null)
		{
			random = new Random();
		}
		return random;
	}
	public  static String generateRandomPassword()
	{
		//String randomPassword = null;
		 
		  String upperAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerAlphabets = "abcdefghijklmnopqrstuvwxyz";
	      String numbers = "0123456789";
	      String alphaNumeric = upperAlphabets+lowerAlphabets+numbers;
	      StringBuilder sb = new StringBuilder();
	      Random random = getRandom();
	      int length = 8;
	      for(int i = 0; i<length; i++)
	      {
	    	 int index = random.nextInt(alphaNumeric.length());
	    	 Character randomChar = alphaNumeric.charAt(index);
	    	 sb.append(randomChar);
	      }
	      //randomPassword = sb.toString();
	      return sb.toString();
	}
      
}
