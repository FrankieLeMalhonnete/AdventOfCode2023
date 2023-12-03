package j1;

import com.google.common.base.*;
import java.util.List;

import utils.Utils;

class J1_1 {

    public static void main(String args[]) {
        List<String> in = Utils.readDataFile("j1/1-1_data.txt");

        int total = 0;
        for (String line : in) {
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
            System.out.println("Total = " + total + " adding [" + added + "] for Current line = " + line);
        }

        System.out.println(total);
    }
}