package j2;

import com.google.common.base.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utils.Utils;

class J2_1 {

    public static final int maxRed = 12;
    public static final int maxGreen = 13;
    public static final int maxBlue = 14;

    public static Game buildGame(String[] str) {
        int red = 0;
        int green = 0;
        int blue = 0;
        for (String elt : str) {
            String digits = CharMatcher.inRange('0','9').retainFrom(elt);
            if (elt.contains("red")) {
                red = Integer.parseInt(digits);
            } else if (elt.contains("green")) {
                green = Integer.parseInt(digits);
            } else if (elt.contains("blue")) {
                blue = Integer.parseInt(digits);
            }
        }
        return new Game(red, green, blue);
    }

    public static List<Game> buildGameList(String[] str) {
        List<Game> games = new ArrayList<Game>();
        for (String game : str) {
            String[] cubes = game.split(",");
            games.add(buildGame(cubes));
        }
        return games;
    }

    public static HashMap<Integer, List<Game>> buildGamesCounts(List<String> gamesList) {
        HashMap<Integer, List<Game>> allGames = new HashMap();
        for (String line : gamesList) {
            String nbSequence = line.substring(5, line.indexOf(":"));
            int gameNb = Integer.parseInt(nbSequence);
            line = line.replace("Game "+nbSequence+": ", "");
            //System.out.println("reformated line = " + line);

            String[] games = line.split(";");
            allGames.put(gameNb, buildGameList(games));

        }
        return allGames;
    }

    public static boolean isPossible(List<Game> games) {
        for (Game game : games) {
            if (game.red > maxRed || game.green > maxGreen || game.blue > maxBlue) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        List<String> in = Utils.readDataFile("j2/j2_data.txt");

        HashMap<Integer, List<Game>> allGames = buildGamesCounts(in);
        int possibleIdSum = 0;

        for (int i = 1 ; i <= allGames.size() ; i++) {
            List<Game> games = allGames.get(Integer.valueOf(i));
            possibleIdSum += (isPossible(games)) ? i : 0;
        }

        System.out.println("Possible games = " + possibleIdSum);
    }
}