package interview.Strings;

//Write a method which given a string, returns a string where every character in the original is doubled. 
//For example, given the string "xyz", return the string "xxyyzz".

public class DoubleCharacter {

	public static void main(String[] args) {
		
		System.out.println(doubleStringUsingRegExpression(null));
		System.out.println(doubleStringUsingRegExpression(""));
		System.out.println(doubleStringUsingRegExpression(" "));
		System.out.println(doubleStringUsingRegExpression("TripleStep"));

		System.out.println(doubleString(null));
		System.out.println(doubleString(""));
		System.out.println(doubleString(" "));
		System.out.println(doubleString("TripleStep"));
	}
	
	/**
	 * Method to double every character in the original string using regular expression
	 * @param inputStr String
	 * @return String
	 */
	public static String doubleStringUsingRegExpression(String inputStr) {
		// If input String is null, return empty string
		if(null == inputStr) return "";
		
		//replace every character with double. If you need n characters, repeat $0 n times
		return inputStr.replaceAll(".", "$0$0");
	}

	/**
	 * Method to double every character in the original string without regular expression
	 * @param inputStr String
	 * @return String
	 */
	public static String doubleString(String inputStr) {

		// If input String is null, return empty string
		if(null == inputStr) return "";
				
		// Use StringBuilder in non multithread environment. Use Stringbuffer in multithread environment
		StringBuilder outputStr = new StringBuilder();
		
		//Input string length
		int inputStrLength = inputStr.length();

		//find each character and append twice in String Builder
		for (int i = 0; i < inputStrLength; i++) {
			// get the current character from string
			char current = inputStr.charAt(i);
			//Append twice in output string
			outputStr.append(current).append(current);
		}
		//convert the String Builder object to String
		return outputStr.toString();
	}
}
