package j1;

import com.google.common.base.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Utils;

class J1_2 {

    static int count_1 = 0;
    static int count_2 = 0;
    static int count_3 = 0;
    static int count_4 = 0;
    static int count_5 = 0;
    static int count_6 = 0;
    static int count_7 = 0;
    static int count_8 = 0;
    static int count_9 = 0;

    enum Numbers {
        //ZERO("zero"),
        ONE("one"),
        TWO("two"),
        THREE("three"),
        FOUR("four"),
        FIVE("five"),
        SIX("six"),
        SEVEN("seven"),
        EIGHT("eight"),
        NINE("nine");

        private String value;
        private Numbers(String value) { this.value = value; }
        private String getValue() { return this.value; }
    }

    static Pattern pattern_1 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.ONE.getValue()));
    static Pattern pattern_2 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.TWO.getValue()));
    static Pattern pattern_3 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.THREE.getValue()));
    static Pattern pattern_4 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.FOUR.getValue()));
    static Pattern pattern_5 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.FIVE.getValue()));
    static Pattern pattern_6 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.SIX.getValue()));
    static Pattern pattern_7 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.SEVEN.getValue()));
    static Pattern pattern_8 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.EIGHT.getValue()));
    static Pattern pattern_9 = Pattern.compile("\\b%s(?!\\w)".format(Numbers.NINE.getValue()));

    private static void debugCount(String line) {
        // Or if you want to avoid string formatting
        //Pattern pattern = Pattern.compile("\\bwants(?!\\w)");
        Matcher matcher_1 = pattern_1.matcher(line);
        while (matcher_1.find()) {count_1++;}
        Matcher matcher_2 = pattern_2.matcher(line);
        while (matcher_2.find()) {count_2++;}
        Matcher matcher_3 = pattern_3.matcher(line);
        while (matcher_3.find()) {count_3++;}
        Matcher matcher_4 = pattern_4.matcher(line);
        while (matcher_4.find()) {count_4++;}
        Matcher matcher_5 = pattern_5.matcher(line);
        while (matcher_5.find()) {count_5++;}
        Matcher matcher_6 = pattern_6.matcher(line);
        while (matcher_6.find()) {count_6++;}
        Matcher matcher_7 = pattern_7.matcher(line);
        while (matcher_7.find()) {count_7++;}
        Matcher matcher_8 = pattern_8.matcher(line);
        while (matcher_8.find()) {count_8++;}
        Matcher matcher_9 = pattern_9.matcher(line);
        while (matcher_9.find()) {count_9++;}
    }

    public static void outputCounters() {
        System.out.println("Count 1 = " + count_1);
        System.out.println("Count 2 = " + count_2);
        System.out.println("Count 3 = " + count_3);
        System.out.println("Count 4 = " + count_4);
        System.out.println("Count 5 = " + count_5);
        System.out.println("Count 6 = " + count_6);
        System.out.println("Count 7 = " + count_7);
        System.out.println("Count 8 = " + count_8);
        System.out.println("Count 9 = " + count_9);
    }


    public static void main(String args[]) {
        List<String> in = Utils.readDataFile("j1/1-1_data.txt");
        int total = 0;

        for (String line : in) {
            System.out.println("Initial line = " + line);
            line = line.toLowerCase();
            debugCount(line);
            //convert text digits to number
            line = line.replace("twone","21");
            line = line.replace("sevenine","79");
            line = line.replace("oneight","18");
            line = line.replace("threeight","38");
            line = line.replace("nineight","98");
            line = line.replace("fiveight","58");
            line = line.replace("eighthree","83");
            line = line.replace("eightwo","82");
            //normal numbers
            line = line.replace(Numbers.ONE.getValue(), "1o");
            line = line.replace(Numbers.TWO.getValue(), "2o");
            line = line.replace(Numbers.THREE.getValue(), "3");
            line = line.replace(Numbers.FOUR.getValue(), "4");
            line = line.replace(Numbers.FIVE.getValue(), "5");
            line = line.replace(Numbers.SIX.getValue(), "6");
            line = line.replace(Numbers.SEVEN.getValue(), "7");
            line = line.replace(Numbers.EIGHT.getValue(), "8");
            line = line.replace(Numbers.NINE.getValue(), "9e");


            String digits = CharMatcher.inRange('0','9').retainFrom(line);
            if (digits.length() > 2) {
                digits = Character.toString(digits.charAt(0))
                       + Character.toString(digits.charAt(digits.length() - 1));
            }
            else if (digits.length() == 1) {
                digits += digits;
            }
            int added = Integer.parseInt(digits);
            total += added;
            System.out.println("Formatted ln = " + line);
            System.out.println("Adding [" + added + "] => Total = " + total);
        }

        System.out.println("========FINAL RESULT========\n" + total);

        outputCounters();
    }
}