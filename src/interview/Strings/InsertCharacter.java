package interview.Strings;

//Write a method which given a string, returns a string with an asterisk inserted between every character in the original.
// Use recursion in your solution.

public class InsertCharacter {

	public static void main(String[] args) {
		//System.out.println(modifyString("TripleStep"));
		System.out.println(modifyString("T"));
		System.out.println(modifyString("TE"));
		System.out.println(modifyString(""));
		System.out.println(modifyString(null));
	}
	
	/**
	 *  Method for adding asterisk character in between every character of the String
	 * @param inputStr String
	 * @return String
	 */
	public static String modifyString(String inputStr) {
		// If input is null, return empty String
		if(null == inputStr) return "";
		
		//If the current input String length is <2, then return the input String
		// If you need '*' char at the end of the String, then change inputStr.length()<2 to inputStr.length()<1.
		if(inputStr.length() < 1) return inputStr;
		
		// Return the first character + '*' , and call the recursion function for remaining string
		return inputStr.charAt(0) + "*" + modifyString(inputStr.substring(1));
	}

}
