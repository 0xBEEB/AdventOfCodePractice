package appRunner.workshopItems;

import java.util.Comparator;

public class GuardTimeSheetItemComparator implements Comparator<GuardTimeSheetItem> {

    @Override
    public int compare(GuardTimeSheetItem o1, GuardTimeSheetItem o2) {
        return o1.getTimeStamp().compareTo(o2.getTimeStamp());
    }

}
