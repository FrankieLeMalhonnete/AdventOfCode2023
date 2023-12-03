package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> readDataFile(String filename) {
        URL path = Utils.class.getResource("../" + filename);
        File file = new File(path.getFile());
        List<String> in = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                in.add(line);
            }
            br.close();
        }
        catch (IOException ioe) {
            System.err.println("Error reading providing file : " + filename);
        }
        return in;
    }
}
