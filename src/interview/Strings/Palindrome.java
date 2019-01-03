package interview.Strings;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findPalindrome("45312343214344543543"));
		//findPalindrome("anna");
	}
	
	public static String findPalindrome(String str) {
		int longStart = 0;
		int longEnd = 0;
		for(int i=0; i<str.length(); i++) {
			for(int j=i+1; j<=str.length(); j++) {
				//System.out.println(i + ":" + j+":"+str.substring(i, j));
				if(checkPalindrome(str.substring(i, j)) && (j-i > longEnd-longStart)) {
					//System.out.println(str.substring(i, j));
					longEnd = j;
					longStart = i;
				}
			}
		}
		return str.substring(longStart, longEnd);
	}
	
	public static boolean checkPalindrome(String input) {
		if(input.length()<2) return false;
		int j = input.length()-1;
		//System.out.println(input);
		for(int i=0; i<input.length()/2; i++) {
			//System.out.println(input.charAt(i)+":"+input.charAt(j));
			if(input.charAt(i) != input.charAt(j--)) {
				return false;
			}
		}
		return true;
	}

}
