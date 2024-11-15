package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Dog extends Character{

    public Dog(int id, String name, int column, int line, int aliveId, int deadId) {
        super(id, aliveId, 3, name, column, line, 2,2,0, aliveId, deadId);
    }

    @Override
    public String[] getCreatureInfo() {
        String[] info = new String[7];
        info[0] = Integer.toString(this.getId());
        info[1] = "Cão";
        info[2] = getName();
        info[3] = Integer.toString(getColumn());
        info[4] = Integer.toString(getLine());
        info[5] = null;
        return info;
    }

    @Override
    public String toString() {
        return getId() + " | Cão | " + getName() + " @ (" + getColumn() + ", " + getLine() + ")";
    }
}
