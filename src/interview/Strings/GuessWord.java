package interview.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode.com/problems/guess-the-word/
 *
 * https://www.youtube.com/watch?v=85pkve4pxTI
 *
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the
 * original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess
 * to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10
 * or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.
 * The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every
 * word in the given word lists is unique.
 *
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 *
 * Explanation:
 *
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 */
public class GuessWord {

  public class Master {

    String secret = "";

    public int guess(String word) {
      return match(word, secret);
    }
  }

  public void findSecretWord(String[] wordlist, Master master) {
    String randomWord;
    int matches;
    for (int i = 0; i < 10; i++) {
      /*
      Take a word from wordlist and guess it.
      Get the matches of this word
      Update our wordlist and keep only the same matches to our guess.
      For example we guess "aaaaaa" and get matches x = 3, we keep the words with exactly 3 a.
       */
      randomWord = wordlist[new Random().nextInt(wordlist.length)];
      matches = master.guess(randomWord);
      if (matches == 6) {
        System.out.println("found word");
        return;
      } else {
        List<String> tempWordList = new ArrayList<>();
        for (String word : wordlist) {
          if (match(randomWord, word) == matches) {
            tempWordList.add(word);
          }
        }
        wordlist = tempWordList.stream().toArray(String[]::new);
      }
    }
    System.out.println("not found");
  }

  /**
   * Also we need to know the matches between two words, so a sub function as following will be helpful.
   */
  public int match(String a, String b) {
    int matches = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == b.charAt(i)) {
        matches++;
      }
    }
    return matches;
  }

  public static void main(String[] args) {
    GuessWord guessWord = new GuessWord();
    String[] wordList = new String[]{"test12", "anbu12", "abbala", "12ba12", "1n34es", "lavany", "ananya", "rihaan",
        "anbala",};

    Master master = guessWord.new Master();
    master.secret = "anbala";
    guessWord.findSecretWord(wordList, master);

    wordList = new String[]{"aazyxw", "aayxwv", "aaxwvu", "aawvut", "aavuts", "aautsr", "aatsrq", "aasrqp", "aarqpo",
        "aaqpon", "aaponm", "aaonml", "aanmlk", "aamlkj", "aalkji", "aakjih", "aajihg", "aaihgf", "aahgfe", "aagfed",
        "aafedc", "ccwwww", "ccssss", "ccoooo", "cckkkk", "ccgggg", "cccccc", "ccyyyy", "ccuuuu", "ccqqqq", "ccmmmm",
        "ddwwww", "ddssss", "ddoooo", "ddkkkk", "ddgggg", "ddcccc", "ddyyyy", "dduuuu", "ddqqqq", "ddmmmm", "eewwww",
        "eessss", "eeoooo", "eekkkk", "eegggg", "eecccc", "eeyyyy", "eeuuuu", "eeqqqq", "eemmmm", "ffwwww", "ffssss",
        "ffoooo", "ffkkkk", "ffgggg", "ffcccc", "ffyyyy", "ffuuuu", "ffqqqq", "ffmmmm", "ggwwww", "ggssss", "ggoooo",
        "ggkkkk", "gggggg", "ggcccc", "ggyyyy", "gguuuu", "ggqqqq", "ggmmmm", "hhwwww", "hhssss", "hhoooo", "hhkkkk",
        "hhgggg", "hhcccc", "hhyyyy", "hhuuuu", "hhqqqq", "hhmmmm", "iiwwww", "iissss", "iioooo", "iikkkk", "iigggg",
        "iicccc", "iiyyyy", "iiuuuu", "iiqqqq", "iimmmm", "jjwwww", "jjssss", "jjoooo", "jjkkkk", "jjgggg", "jjcccc",
        "jjyyyy", "jjuuuu", "jjqqqq"};

    master.secret = "aaponm";
    guessWord.findSecretWord(wordList, master);
  }
}
