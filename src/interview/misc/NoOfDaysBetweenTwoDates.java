package interview.misc;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Find number of days between two given dates
 * Given two dates, find total number of days between them. The count of days must be calculated in O(1) time and O
 * (1) auxiliary space.
 * Examples:
 *
 * Input: dt1 = {10, 2, 2014}
 * dt2 = {10, 3, 2015}
 * Output: 393
 * dt1 represents "10-Feb-2014" and dt2 represents "10-Mar-2015"
 * The difference is 365 + 28
 *
 * Input: dt1 = {10, 2, 2000}
 * dt2 = {10, 3, 2000}
 * Output: 29
 * Note that 2000 is a leap year
 *
 * Input: dt1 = {10, 2, 2000}
 * dt2 = {10, 2, 2000}
 * Output: 0
 * Both dates are same
 */
public class NoOfDaysBetweenTwoDates {

  // To store number of days in all months from January to Dec.
  static int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  /**
   * Using Java temporal Function to find difference between two dates
   */
  public static void noOfDaysBetweenTwoDates(String strDate1, String strDate2) {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");

    // Convert String to Date
    LocalDate date1 = LocalDate.parse(strDate1, dateFormat);
    LocalDate date2 = LocalDate.parse(strDate2, dateFormat);

    long diffInDays = DAYS.between(date1, date2);

    System.out.println("Days different: " + diffInDays);

    // Convert Date to String
    System.out.println("Date in String Format:" + date1.format(dateFormat));
  }

  /**
   * https://www.geeksforgeeks.org/find-number-of-days-between-two-given-dates/
   */
  public static void noOfDaysBetweenTwoDates_Without_Java_LocalDate(String strDate1, String strDate2) {
    String[] strArray1 = strDate1.split("/");
    String[] strArray2 = strDate2.split("/");

    if (strArray1.length != 3 || strArray2.length != 3) {
      throw new RuntimeException("Illegal Fomat");
    }

    int noOfDays_date1 = getTotalDays(strArray1);
    noOfDays_date1 += countLeapYears(Integer.parseInt(strArray1[1]), Integer.parseInt(strArray1[2]));
    int noOfDays_date2 = getTotalDays(strArray2);
    noOfDays_date2 += countLeapYears(Integer.parseInt(strArray2[1]), Integer.parseInt(strArray2[2]));

    int daysDifference = Math.abs(noOfDays_date1 - noOfDays_date2);
    System.out.println("DaysDifference:" + daysDifference);
  }

  private static int getTotalDays(String[] strArray) {
    // initialize count using years and day
    int noOfDays = Integer.parseInt(strArray[2]) * 365 + Integer.parseInt(strArray[1]);

    // Add days for months in given date
    int month = Integer.parseInt(strArray[0]) - 1;
    for (int i = 0; i < month; i++) {
      noOfDays += monthDays[i];
    }
    return noOfDays;
  }

  // This function counts number of leap years before the given date
  static int countLeapYears(int months, int years) {

    // Check if the current year needs to be considered
    // for the count of leap years or not
    if (months <= 2) {
      years--;
    }

    // An year is a leap year if it is a multiple of 4,
    // multiple of 400 and not a multiple of 100.
    return years / 4 - years / 100 + years / 400;
  }

  public static void main(String[] args) {

    System.out.println("*********** Find Days Difference Using Java Local Date *************");
    LocalDateTime localDate = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // convert DateTime to String
    System.out.println(localDate.format(dateTimeFormatter));

    noOfDaysBetweenTwoDates("2/15/2018", "2/18/2018");
    noOfDaysBetweenTwoDates("3/4/2019", "10/5/2018");

    System.out.println("\n\n\n*********** Find Days Difference Without Using Java Local Date *************");
    noOfDaysBetweenTwoDates_Without_Java_LocalDate("12/15/2018", "12/18/2018");
  }
}
