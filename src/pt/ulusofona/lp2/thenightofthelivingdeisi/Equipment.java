package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {
    private int id;
    private int type;
    private boolean inPlay;
    private boolean beingUsed;
    private int column;
    private int line;

    public Equipment(int id, int tipo, int column, int line) {
        this.id = id;
        this.type = tipo;
        this.column = column;
        this.line = line;
        this.inPlay = true;
        this.beingUsed = false;
    }

    public String[] getEquipmentInfo() {
        String[] info = new String[5];
        info[0] = Integer.toString(id);
        info[1] = Integer.toString(type);
        info[2] = Integer.toString(column);
        info[3] = Integer.toString(line);
        info[4] = null;
        return info;
    }

    public int getTipo() {
        return type;
    }

    public int getId() {
        return id;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public boolean isBeingUsed() {
        return beingUsed;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public void gotPickedUp() {
        beingUsed = true;
    }

    public void gotDestroyed() {
        inPlay = false;
        beingUsed = false;
    }

    @Override
    public String toString() {
        if (type == 0) {
            return id + " | Escudo de madeira @ (" + column + "," + line + ')';
        } else if (type == 1) {
            return id + " | Espada samurai @ (" + column + "," + line + ')';
        }
        return "Wrong type";
    }
}
