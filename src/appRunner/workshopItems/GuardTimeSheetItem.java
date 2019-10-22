package appRunner.workshopItems;

import java.util.Date;

public class GuardTimeSheetItem {

    private String raw;
    private Date timeStamp;
    private GuardAction action;

    public GuardTimeSheetItem(String raw, Date timeStamp, GuardAction action) {
        this.raw = raw;
        this.timeStamp = timeStamp;
        this.action = action;
    }

    public String getRaw() {
        return raw;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public GuardAction getAction() {
        return action;
    }

    public void setAction(GuardAction action) {
        this.action = action;
    }


}
