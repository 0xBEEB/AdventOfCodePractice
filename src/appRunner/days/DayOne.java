package appRunner.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class DayOne extends Day {

    private Integer parseLine(String line) {
        int sign = line.substring(0,1).equals("+") ? 1 : -1;
        return sign * Integer.parseInt(line.substring(1));
    }

    @Override
    public void solve() {
        System.out.println("Hello AC01!");
        LinkedList<String> lines = readLines("DayOneInput.txt");
        Integer frequency = 0;

        for (int i = 0; i < lines.size(); i++) {
            frequency += parseLine(lines.get(i));
        }

        System.out.println("part1: " + frequency);

        HashMap<Integer, Boolean> seenMap = new HashMap<>();
        Boolean stillLoop = Boolean.TRUE;
        frequency = 0;
        while (stillLoop) {

            for (int i = 0; i < lines.size(); i++) {
                if (seenMap.containsKey(frequency)) {
                    stillLoop = Boolean.FALSE;
                    System.out.println("part2: " + frequency);
                    break;
                }
                seenMap.put(frequency, Boolean.TRUE);
                frequency += parseLine(lines.get(i));
            }
        }
    }
}
