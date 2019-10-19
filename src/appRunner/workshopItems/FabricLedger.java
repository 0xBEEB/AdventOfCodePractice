package appRunner.workshopItems;

import java.util.ArrayList;

public class FabricLedger {

    ArrayList<String> ids;

    public FabricLedger() {
        ids = new ArrayList<>();
    }

    public void addId(String id) {
        if (!ids.contains(id)) {
            ids.add(id);
        }
    }

    public int getIdCount() {
        return ids.size();
    }
}
