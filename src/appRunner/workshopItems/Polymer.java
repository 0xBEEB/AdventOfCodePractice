package appRunner.workshopItems;

import java.util.LinkedList;

public class Polymer extends LinkedList<Unit> {

    private String input;

    public Polymer(String input) {
        this.input = input;
        for(char c : input.toCharArray()) {
            add(new Unit(c));
        }
    }

    public Unit triggerReaction() {
        Unit prev = null;
        int idx = 0;
        for (Unit u : this) {
            if (prev == null || (u.getValue() != prev.getOpposite().getValue())) {
                prev = u;
                idx += 1;
            } else {
                remove(idx);
                remove(idx - 1);
                return u;
            }
        }
        return null;
    }

    public void reduce() {
        while (triggerReaction() != null) {
            ;;
        }
    }

    public Polymer removeAll(Unit u) {
        String newInput = input;
        newInput = newInput.replaceAll(Character.toString(u.getValue()), "");
        newInput = newInput.replaceAll(Character.toString(u.getOpposite().getValue()), "");
        return new Polymer(newInput);
    }

    @Override
    public String toString() {
        String out = "";
        for (Unit u : this) {
            out += u.getValue();
        }
        return out;
    }
}
