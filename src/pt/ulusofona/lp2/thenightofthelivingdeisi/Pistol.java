package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Pistol extends Equipment{
    private int bullets = 3;

    public Pistol(int id, int type, int column, int line) {
        super(id, type, column, line, true);
    }

    @Override
    public boolean shoots() {
        if (bullets <= 0) {
            return false;
        }
        bullets--;
        return true;
    }


    @Override
    public String getEquipmentInfoAsString() {
        return id + " | Pistola Walter PPK @ (" + column + "," + line + ") | " + bullets + " balas";
    }


    @Override
    public String toString() {
        if (!inPlay || beingUsed) {
            return null;
        }
        return id + " | Pistola Walter PPK @ (" + column + "," + line + ") | " + bullets + " balas";
    }
}
