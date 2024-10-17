package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Character {
    private String name;
    private int id;
    private int team;
    private int equipmentCount;
    private Equipment equipment;
    private int column;
    private int line;
    private boolean inPlay;

    public Character(int id, int team, String name, int column, int line) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.column = column;
        this.line = line;
        this.inPlay = true;
        this.equipmentCount = 0;
        this.equipment = null;
    }

    public String[] getCreatureInfo() {
        String[] info = new String[6];
        info[0] = Integer.toString(id);
        switch (team) {
            case 1 ->
                info[1] = "Humano";
            case 0 ->
                info[1] = "Zombie";
            default ->
                info[1] = "";
        }
        info[2] = name;
        info[3] = Integer.toString(column);
        info[4] = Integer.toString(line);
        info[5] = null;
        return info;
    }

    public boolean hasEquipment(int equipmentTypeId) {
        return equipment != null && equipment.getTipo() == equipmentTypeId;
    }

    public void changeCoordinates(int column, int line) {
         this.column = column;
         this.line = line;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTeam() {
        return team;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void pickUpEquipment(Equipment equipment) {
        this.equipment = equipment;
        equipment.gotPickedUp();
        equipmentCount++;
    }

    public void destroyEquipment(Equipment equipment) {
        equipment.gotDestroyed();
        equipmentCount++;
    }

    @Override
    public String toString() {
        if (team == 1) {
            return id + " | Humano | " + name + " | +" + equipmentCount + " @ (" + column + "," + line + ")";
        } else {
            return id + " | Zombie | " + name + " | -" + equipmentCount + " @ (" + column + "," + line + ")";
        }

    }
}
