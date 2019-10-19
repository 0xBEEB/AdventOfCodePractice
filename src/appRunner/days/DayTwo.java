package appRunner.days;

import java.util.HashMap;
import java.util.LinkedList;

public class DayTwo extends Day {

    private Boolean containsDouble(String line) {
        HashMap<Character, Integer> countMap = getCounts(line);

        return countMap.containsValue(2);
    }

    private Boolean containsTriple(String line) {
        HashMap<Character, Integer> countMap = getCounts(line);

        return countMap.containsValue(3);
    }

    private String compareTwoIds(String one, String two) {
        if (one.length() != two.length()) {
            return null;
        }
        int diffs = 0;
        for (int i = 0; i < one.length(); i++) {
            if (one.charAt(i) != two.charAt(i)) {
                diffs++;
            }
        }

        if (diffs == 1) {
            return (one + " : " + two);
        }
        return null;
    }

    private String findCorrectIds(String line, LinkedList<String> lines) {
        for (String curr : lines) {
            String out = compareTwoIds(line, curr);
            if (out != null) {
                return out;
            }
        }
        return null;
    }

    private HashMap<Character, Integer> getCounts(String line) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        for (int i = 0; i < line.length(); i++) {
            if (countMap.containsKey(line.charAt(i))) {
                countMap.put(line.charAt(i), countMap.get(line.charAt(i)) + 1);
            } else {
                countMap.put(line.charAt(i), 1);
            }
        }
        return countMap;
    }

    @Override
    public void solve() {
        System.out.println("Hello AC02!");
        LinkedList<String> lines = readLines("DayTwoInput.txt");
        int doubles = 0;
        int triples = 0;

        for (String line : lines) {
            if (containsDouble(line)) {
                doubles++;
            }
            if (containsTriple(line)) {
                triples++;
            }
        }

        System.out.println("part1: " + (doubles * triples));

        for (String line : lines) {
            String out = findCorrectIds(line, lines);
            if (out != null) {
                System.out.println("part2: " + out);
                break;
            }
        }
    }
}
