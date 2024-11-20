package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Bleach extends Equipment{
    private float capacity = 1;

    public Bleach(int id, int type, int column, int line) {
        super(id, type, column, line, false);
    }

    @Override
    public boolean bleachCanBeUsed() {
        float usage = 0.3F;
        if (capacity <= 0) {
            return false;
        }
        capacity -= usage;
        capacity = Math.round(capacity * 10) / 10.0F;
        if (capacity < 0) {
            capacity = 0;
        }
        return true;
    }

    @Override
    public String getEquipmentInfoAsString() {
        return id + " | Lixívia @ (" + column + ", " + line + ") | " + capacity + " litros";
    }


    @Override
    public String toString() {
        if (!inPlay || beingUsed) {
            return null;
        }
        return id + " | Lixívia @ (" + column + ", " + line + ") | " + capacity + " litros";
    }
}
