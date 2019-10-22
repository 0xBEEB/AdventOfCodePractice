package appRunner.days;

import appRunner.workshopItems.Guard;
import appRunner.workshopItems.GuardAction;
import appRunner.workshopItems.GuardTimeSheetItem;
import appRunner.workshopItems.GuardTimeSheetItemComparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class DayFour extends Day {

    public Date parseDate(String line) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        int start = line.indexOf('[') + 1;
        int end = line.indexOf(']');
        return sdf.parse(line.substring(start, end));
    }

    public GuardAction parseAction(String line) {
        if (line.endsWith("begins shift")) {
            return GuardAction.BEGINS_SHIFT;
        } else if (line.endsWith("wakes up")) {
            return GuardAction.WAKES_UP;
        } else if (line.endsWith("falls asleep")) {
            return GuardAction.FALLS_ASLEEP;
        } else {
            return GuardAction.UNDEFINED;
        }
    }

    public GuardTimeSheetItem parseLine(String line) throws ParseException {
        Date timeStamp = parseDate(line);
        GuardAction action = parseAction(line);
        return new GuardTimeSheetItem(line, timeStamp, action);
    }

    public String parseId(String line) {
        int start = line.indexOf('#') + 1;
        int end = line.indexOf("begins") - 1;
        return line.substring(start, end);
    }

    @Override
    public void solve() {
        System.out.println("Hello AC04!");
        LinkedList<String> lines = readLines("DayFourInput.txt");
        ArrayList<GuardTimeSheetItem> timeSheet = new ArrayList<>();

        for (String line : lines) {
            try {
                GuardTimeSheetItem item = parseLine(line);
                timeSheet.add(item);
            } catch (ParseException e) {
                System.err.println("Error parsing input: \"" + line + "\"");
            }
        }

        timeSheet.sort(new GuardTimeSheetItemComparator());

        HashMap<String, Guard> guards = new HashMap<>();
        Guard currentGuard = null;

        for (GuardTimeSheetItem item : timeSheet) {
            switch (item.getAction()) {
                case BEGINS_SHIFT:
                    String id = parseId(item.getRaw());
                    if (currentGuard != null) {
                        currentGuard.getTimesheet().add(
                                new GuardTimeSheetItem("", item.getTimeStamp(), GuardAction.ENDS_SHIFT)
                        );
                    }
                    currentGuard = new Guard(id);
                    if (!guards.containsKey(id)) {
                        guards.put(id, currentGuard);
                    }
                    currentGuard.getTimesheet().add(item);
                case WAKES_UP:
                case ENDS_SHIFT:
                case FALLS_ASLEEP:
                case UNDEFINED:
                default:
                    if (currentGuard != null) {
                        currentGuard.getTimesheet().add(item);
                    }
            }
        }

        Guard maxSleepGuard = null;
        for (Guard guard : guards.values()) {
            if (maxSleepGuard == null || guard.getMinutesAsleep() > maxSleepGuard.getMinutesAsleep()) {
                maxSleepGuard = guard;
            }
        }

        System.out.println("part1: " + maxSleepGuard.getId());
    }
}
