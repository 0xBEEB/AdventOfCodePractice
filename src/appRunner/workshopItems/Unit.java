package appRunner.workshopItems;

public class Unit {

    private char value;

    public Unit(char val) {
        setValue(val);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Unit getOpposite() {
        if (Character.isLowerCase(value)) {
            return new Unit(Character.toUpperCase(value));
        } else {
            return new Unit(Character.toLowerCase(value));
        }
    }

    public boolean isOpposite(Unit u) {
        return getOpposite() == u;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Unit)) return false;
        return ((Unit) obj).getValue() == getValue();
    }
}
