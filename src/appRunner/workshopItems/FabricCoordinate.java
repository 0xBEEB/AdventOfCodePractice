package appRunner.workshopItems;

import java.util.ArrayList;
import java.util.Objects;

public class FabricCoordinate {

    private int x;
    private int y;

    public FabricCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof FabricCoordinate)) return false;

        FabricCoordinate coord = (FabricCoordinate) o;
        if (this.x == coord.x && this.y == coord.y) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
