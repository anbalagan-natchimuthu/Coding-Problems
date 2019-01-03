package interview.Strings;

public class DuplicateCharInString {

	public static void main(String[] args) {
		System.out.println(checkDuplicateCharacter(" ab&*~!@ "));
		System.out.println(permutation("abc", "acb"));
	}

	// Implement an algorithm to determine if a string has all unique
	// characters. What if
	// you cannot use additional data structures
	public static boolean checkDuplicateCharacter(String input) {
		boolean[] arr = new boolean[256];
		for (int i = 0; i < input.length(); i++) {
			int val = input.charAt(i);
			if (arr[val]) {
				System.out.println("already exist");
				return false;
			}
			arr[val] = true;
		}
		return true;
	}

	// Given two Strings, write a method to decide if one is a permutation of
	// the other.
	public static boolean checkPermutation(String one, String two) {
		if (one.length() != two.length()) {
			return false;
		}
		return sort(one).equals(sort(two));
	}

	private static String sort(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}

	public static boolean permutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		int[] letters = new int[256]; // Assumption

		char[] s_array = s.toCharArray();
		for (char c : s_array) { // count number of each char in s.
			letters[c]++;
		}

		for (int i = 0; i < t.length(); i++) {
			int c = t.charAt(i);
			if (--letters[c] < 0) {
				return false;
			}
		}
		return true;
	}

}
