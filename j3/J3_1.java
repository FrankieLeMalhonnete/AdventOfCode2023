package j3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.Utils;

class J3_1 {
    public static String symbolsStrFull = "^[^<>{}\"/|;:,~!?@#$%^=&*\\]\\\\()\\[¿§«»ω⊙¤°℃℉€¥£¢¡®©_+-]*$";
    public static Pattern regex = Pattern.compile("[$&+,:;=?@#|<>*()%//!-]");
    public static Pattern exactFromData = Pattern.compile("[*/#&+@=$%-]");

    public static List<Part> partInventory(List<String> schematics) {
        List<Part> parts = new ArrayList<Part>();
        Pattern pattern = Pattern.compile("\\d+");
        int fromCursor = 0;

        for (int i = 0; i < schematics.size(); i++) {
            String line = schematics.get(i);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int value = Integer.parseInt(matcher.group());
                int startIndex = line.indexOf(String.valueOf(value), fromCursor);
                int valueLength = (int)(Math.log10(value));
                fromCursor = startIndex + valueLength;
                parts.add(new Part(i, startIndex, startIndex + valueLength, value));
            }
            fromCursor = 0;
        }
        return parts;
    }

    public static int determineConnected(List<Part> parts, List<String> schematics) {
        String prevLine = "";
        String currLine = "";
        String nextLine = "";
        int sumPartsId = 0;

        for (Part part : parts) {
            String line;
            //Initialize surrounding lines
            if (part.line != 0) {
                line = schematics.get(part.line - 1);
                prevLine = line.substring((part.startIndex > 0 ? part.startIndex -1 : 0), (part.endIndex < line.length()-1) ? part.endIndex +2 : line.length() -1);
            }
            line = schematics.get(part.line);
            currLine = line.substring((part.startIndex > 0 ? part.startIndex -1 : 0), (part.endIndex < line.length()-1) ? part.endIndex +2 : line.length() -1);
            if (part.line != schematics.size()-1) {
                line = schematics.get(part.line + 1);
                nextLine = line.substring((part.startIndex > 0 ? part.startIndex -1 : 0), (part.endIndex < line.length()-1) ? part.endIndex +2 : line.length() -1);
            } else { nextLine = ""; }

            //DEBUG surroundings :
            System.out.println(prevLine);
            System.out.println(currLine);
            System.out.println(nextLine);

            //Searching for connectors
            String fullSequence = prevLine.concat(currLine).concat(nextLine).replace('.', ' ');
            Matcher matcher = exactFromData.matcher(fullSequence);
            if (matcher.find()) {
                //System.out.println("Part is connected !");
                part.isConnected = true;
                sumPartsId += part.value;
            } else {
                System.out.println("Part is not connected ! Check line #" + (part.line+1));
            }
            System.out.println("=====================");
        }
        return sumPartsId;
    }

     public static void main(String args[]) {
        List<String> in = Utils.readDataFile("j3/j3_data.txt");
        List<Part> parts = partInventory(in);

        int sumConnectedParts = determineConnected(parts, in);
        System.out.println("NbParts = " + parts.size());
        System.out.println("Connected parts sum = " + sumConnectedParts);
    }
}