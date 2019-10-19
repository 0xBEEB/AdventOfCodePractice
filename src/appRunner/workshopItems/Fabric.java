package appRunner.workshopItems;

import java.util.*;

public class Fabric {

    private HashMap<FabricCoordinate, FabricLedger> fabricMap;

    public Fabric() {
        fabricMap = new HashMap<>();
    }

    public void claim(String id, FabricCoordinate coord, int width, int height) {
        for (int x = coord.getX(); x < coord.getX() + width; x++) {
            for (int y = coord.getY(); y < coord.getY() + height; y++) {
                FabricCoordinate curr = new FabricCoordinate(x, y);
                if (fabricMap.containsKey(curr)) {
                    fabricMap.get(curr).addId(id);
                } else {
                    FabricLedger ledger = new FabricLedger();
                    ledger.addId(id);
                    fabricMap.put(curr, ledger);
                }
            }
        }
    }

    public int getOverlappingCount() {
        Collection<FabricLedger> values = fabricMap.values();
        int currCount = 0;
        for (FabricLedger v : values) {
            if (v.getIdCount() > 1) currCount++;
        }
        return currCount;
    }

    public String getFirstIdOfNotOverlapping() {
        HashMap<String, ArrayList<FabricLedger>> reverseMap = new HashMap<>();

        for (FabricLedger ledger : fabricMap.values()) {
            for (String id : ledger.ids) {
                if (reverseMap.containsKey(id)) {
                    reverseMap.get(id).add(ledger);
                } else {
                    ArrayList<FabricLedger> ledgers = new ArrayList<>();
                    ledgers.add(ledger);
                    reverseMap.put(id, ledgers);
                }
            }
        }

        Set<String> ids = ((HashMap<String, ArrayList>)reverseMap.clone()).keySet();
        for (String id : reverseMap.keySet()) {
            for (FabricLedger ledger : reverseMap.get(id)) {
                if (ledger.ids.size() > 1) {
                    ids.remove(id);
                }
            }
        }

        return ids.iterator().next();
    }

    public HashMap<FabricCoordinate, FabricLedger> getFabricMap() {
        return fabricMap;
    }
}
