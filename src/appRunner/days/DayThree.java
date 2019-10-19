package appRunner.days;

import appRunner.workshopItems.Fabric;
import appRunner.workshopItems.FabricCoordinate;

import java.util.LinkedList;

public class DayThree extends Day {

    public void parseLine(String line, Fabric fabric) {
        int startId = line.indexOf("#") + 1;
        int endId = line.indexOf("@") - 1;
        int startCoord = line.indexOf("@") + 2;
        int colon = line.indexOf(":");
        int startSpread = colon + 2;
        int coordDelim = line.indexOf(",");
        int spreadDelim = line.indexOf("x");

        FabricCoordinate coord = new FabricCoordinate(
                Integer.parseInt(line.substring(startCoord, coordDelim)),
                Integer.parseInt(line.substring(coordDelim + 1, colon)));

        fabric.claim(
                line.substring(startId, endId),
                coord,
                Integer.parseInt(line.substring(startSpread, spreadDelim)),
                Integer.parseInt(line.substring(spreadDelim + 1))
        );
    }


    @Override
    public void solve() {
        System.out.println("Hello AC03!");
        LinkedList<String> lines = readLines("DayThreeInput.txt");

        Fabric fabric = new Fabric();

        for (String line : lines) {
            parseLine(line, fabric);
        }

        System.out.println("part1: " + fabric.getOverlappingCount());

        System.out.println("part2: " + fabric.getFirstIdOfNotOverlapping());
    }
}
