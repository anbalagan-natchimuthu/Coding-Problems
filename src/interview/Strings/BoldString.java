package interview.Strings;

/*

// >>> embolden_substrings('abcxyz', [])
// 'abcxyz'

// >>> embolden_substrings('abcxyzabc', ['abc'])
// '<b>abc</b>xyz<b>abc</b>'

// >>> embolden_substrings('abcxyz123', ['acx', '123'])
// 'abcxyz<b>123</b>'

// >>> embolden_substrings('abcxyz1234abc', ['abc', '123'])
// '<b>abc</b>xyz<b>123</b>4<b>abc</b>'

// >>> embolden_substrings('abcdxyz1234abc', ['abc', 'bcd'])
// '<b>abcd</b>xyz1234<b>abc</b>'
 */

class BoldString {
    public static void main(String[] args) {
        String output = embolden_substrings_approach1("abcxyz1234abc", new String[] {"abc", "123"});
        System.out.println(output);
    }

    public static String embolden_substrings_approach1 (String inputString, String[] subStrings) {
        if (subStrings == null || subStrings.length < 1) {
            return inputString;
        }

        for (String string : subStrings) {
            inputString = inputString.replaceAll(string, "<b>" + string + "</b>");
        }

        return inputString;
    }

    public static String embolden_substrings (String inputString, String[] subStrings) {
        if (subStrings == null || subStrings.length < 1) {
            return inputString;
        }

        for (String string : subStrings) {
            inputString = boldString(inputString, string, 0);
        }

        return inputString;
    }

    private static String boldString (String inputString, String subString, int index) {
        int indexVal = inputString.indexOf(subString, index);

        if (indexVal != -1) {
            inputString = inputString.substring(0, indexVal) + "<b>" + subString +             "</b>" + inputString.substring(indexVal + subString.length());
            return boldString(inputString, subString, indexVal + subString.length() + 7);
        } else {
            return inputString;
        }
    }
}


