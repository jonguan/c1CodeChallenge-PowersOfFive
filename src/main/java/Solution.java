package main.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mohamed Khader
 *
 */
public class Solution {


	final String binaryNumberFiveRegEx = "(101)";
	
    public Solution(){
    }

    /**
     * Takes in an array of strings, and returns an array of answers
     * @param s - string input array
     * @return - array of int answers of how many powers of five fit into each string in the array
     */
    public int[] getMin(String[] s){
    	
    	if (s != null && s.length > 0)
    	{
    		// create output array
        	int[] output = new int[s.length];
        	
        	// Go thru each string in the array
        	for (int i=0; i < s.length; i++)
        	{
        		// Check to see if the string is power of 5
	        	if (isPowerOfFive(s[i]))
	        	{
	        		output[i]=1;
	        	}
	        	else
	        	{
	        		// Count number of binary Five pattens in the string
	        		int countOfFive = countBinaryFivePattern(s[i]);
	        		output[i] = countOfFive > 0 ? countOfFive: -1;
	        	}
        	}
        	
        	return output;
    	}
    	return null;

    }

	/**
	 * @param input - string input
	 * @return -- integer count of binary pattern of 5
	 */
	private int countBinaryFivePattern(String input) {
    	Pattern p = Pattern.compile(binaryNumberFiveRegEx);
    	int count=0;
    	// Use thread safety
    	// As matcher is not thread safe
    	synchronized (this) {
        	Matcher m = p.matcher(input);
        	while (m.find())
        	{
        		count++;
        	}
		}
    	return count;
	}

	/**
	 * Determines if given binary number is power of 5
	 * @param input - string input 
	 * @return - boolean true or false
	 */
	private boolean isPowerOfFive(String input) {
		if (input != null && 
			!input.isEmpty() && 
			input.startsWith("1") && 
			input.endsWith("101")
			)
		{
			long decimalValue = binaryToDecimal(input);
			if (decimalValue == 5 || decimalValue%25 == 0 )
				return true;
		}
		return false;
	}

	/**
	 * This method converts binary string to a decimal number
	 * @param input - string input of binary 
	 * @return - decimal value
	 */
	private long binaryToDecimal(String input) {
		int length = input.length();
		byte[] binaryBytes = input.getBytes();
		long decimalNumber=0;
		for (int i=0; i < length; i++)
		{
			int k = length-i-1;
			int digit = binaryBytes[i] - 0x30;
			decimalNumber +=  digit * Math.pow(2, k);
		}
		return decimalNumber;
	}
}
