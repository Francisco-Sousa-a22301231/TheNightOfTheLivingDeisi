package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Equipment {
    protected int id;
    protected int type;
    protected boolean inPlay;
    protected boolean beingUsed;
    protected int column;
    protected int line;
    protected boolean offensive;

    public Equipment(int id, int type, int column, int line, boolean offensive) {
        this.id = id;
        this.type = type;
        this.column = column;
        this.line = line;
        this.inPlay = true;
        this.beingUsed = false;
        this.offensive = offensive;
    }

    public String[] getEquipmentInfo() {
        if (inPlay && !beingUsed) {
            String[] info = new String[5];
            info[0] = Integer.toString(id);
            info[1] = Integer.toString(type);
            info[2] = Integer.toString(column);
            info[3] = Integer.toString(line);
            info[4] = null;
            return info;
        }
        return null;
    }

    public String getEquipmentInfoAsString() {
        if (type == 0) {
            return id + " | Escudo de madeira @ (" + column + ", " + line + ')';
        } else if (type == 1) {
            return id + " | Espada samurai @ (" + column + ", " + line + ')';
        }
        return "Wrong type";
    }

    public int getTipo() {
        return type;
    }

    public int getId() {
        return id;
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

    public boolean isOffensive() {
        return offensive;
    }

    public int getType() {
        return type;
    }

    public boolean shoots() {
        return false;
    }

    public boolean bleachCanBeUsed() {
        return false;
    }

    public void moveEquipment(int column, int line) {
        this.column = column;
        this.line = line;
    }

    @Override
    public String toString() {
        if (!inPlay || beingUsed) {
            return null;
        }
        if (type == 0) {
            return id + " | Escudo de madeira @ (" + column + ", " + line + ')';
        } else if (type == 1) {
            return id + " | Espada samurai @ (" + column + ", " + line + ')';
        }
        return "Wrong type";
    }
}
