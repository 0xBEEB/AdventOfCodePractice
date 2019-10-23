package appRunner.workshopItems;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Guard {

    private String id;
    private ArrayList<GuardTimeSheetItem> timesheet;

    public Guard(String id) {
        this.id = id;
        timesheet = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<GuardTimeSheetItem> getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(ArrayList<GuardTimeSheetItem> timesheet) {
        this.timesheet = timesheet;
    }

    public long getMinutesAsleep() {
        timesheet.sort(new GuardTimeSheetItemComparator());
        Date startedSleep = null;
        long minutesAsleep = 0;
        for (GuardTimeSheetItem item : timesheet) {
            if (item.getAction() == GuardAction.FALLS_ASLEEP) {
                startedSleep = item.getTimeStamp();
            }
            if (startedSleep != null &&
                (item.getAction() == GuardAction.WAKES_UP)){
                long sleepDuration = item.getTimeStamp().getTime() - startedSleep.getTime();
                minutesAsleep += TimeUnit.MILLISECONDS.toMinutes(sleepDuration);
            }
        }
        return minutesAsleep;
    }

    private HashMap<String, Integer> getMinuteLog() {
        HashMap<String, Integer> minuteLog = new HashMap<>();
        timesheet.sort(new GuardTimeSheetItemComparator());
        Date startedSleep = null;
        for (GuardTimeSheetItem item : timesheet) {
            if (item.getAction() == GuardAction.FALLS_ASLEEP) {
                startedSleep = item.getTimeStamp();
            }
            if (startedSleep != null &&
                    (item.getAction() == GuardAction.WAKES_UP)){
                final Calendar c = Calendar.getInstance();
                c.setTime(startedSleep);
                long end = item.getTimeStamp().getTime();
                for (; c.getTimeInMillis() < end; c.add(Calendar.MINUTE, 1)) {
                    String minute = "" + c.get(Calendar.MINUTE);
                    if (minuteLog.containsKey(minute)) {
                        minuteLog.put(minute, minuteLog.get(minute) + 1);
                    } else {
                        minuteLog.put(minute, 1);
                    }
                }
                startedSleep = null;
            }
        }
        return minuteLog;
    }

    public String getMinuteMostLikelyAsleep() {
        HashMap<String, Integer> minuteLog = getMinuteLog();

        String mostLikelyMinute = "";
        int mostLikelyValue = 0;
        for (String key : minuteLog.keySet()) {
            if (minuteLog.get(key) > mostLikelyValue) {
                mostLikelyMinute = key;
                mostLikelyValue = minuteLog.get(key);
            }
        }
        return mostLikelyMinute;
    }

    public int getMaxMinuteValue() {
        HashMap<String, Integer> minuteLog = getMinuteLog();

        int mostLikelyValue = 0;
        for (String key : minuteLog.keySet()) {
            if (minuteLog.get(key) > mostLikelyValue) {
                mostLikelyValue = minuteLog.get(key);
            }
        }
        return mostLikelyValue;
    }
}
