package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Vampire extends Character{

    public Vampire(int id, String name, int column, int line, int aliveId, int deadId) {
        super(id, deadId, 4, name, column, line, 1,1,1, aliveId, deadId);
    }

    @Override
    public String[] getCreatureInfo() {
        String[] info = new String[7];
        info[0] = Integer.toString(this.getId());
        info[1] = "Vampiro";
        info[2] = getName();
        info[3] = Integer.toString(getColumn());
        info[4] = Integer.toString(getLine());
        info[5] = null;
        return info;
    }

    @Override
    public String toString() {
        return getId() + " | Vampiro | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
    }
}
