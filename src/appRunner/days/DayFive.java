package appRunner.days;

import appRunner.workshopItems.Polymer;
import appRunner.workshopItems.Unit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DayFive extends Day {

    @Override
    public void solve() {
        System.out.println("Hello AC05!");
        LinkedList<String> lines = readLines("DayFiveInput.txt");

        String orig = lines.getFirst();
        Polymer polymer = new Polymer(orig);
        polymer.reduce();

        System.out.println("part1: " + polymer.size());

        HashMap<Unit,Integer> lengths = new HashMap<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (Character c : alphabet.toCharArray()) {
            Unit u = new Unit(c);
            Polymer uPolymer = new Polymer(orig).removeAll(u);
            uPolymer.reduce();
            lengths.put(u, uPolymer.size());
        }

        int minSize = Integer.MAX_VALUE;
        for (Map.Entry<Unit,Integer> entry : lengths.entrySet()) {
            if (entry.getValue() < minSize) {
                minSize = entry.getValue();
            }
        }

        System.out.println("part2: " + minSize);
    }
}
