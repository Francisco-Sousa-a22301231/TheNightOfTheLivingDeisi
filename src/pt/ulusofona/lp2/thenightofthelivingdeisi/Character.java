package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Character {
    private String name;
    private int id;
    private int team;
    private int originalTeam;
    private int type;
    protected int equipmentCount;
    private Equipment equipment;
    private int column;
    private int line;
    private boolean inPlay;
    protected int verticalMove;
    protected int horizontalMove;
    protected int diagonalMove;
    private int aliveId;
    private int deadId;
    private boolean inSafeHaven = false;

    public Character(int id, int team, int type, String name, int column, int line, int verticalMove, int horizontalMove, int diagonalMove, int aliveId, int deadId) {
        this.id = id;
        this.team = team;
        this.aliveId = aliveId;
        this.deadId = deadId;
        this.type = type;
        this.originalTeam = team;
        this.name = name;
        this.column = column;
        this.line = line;
        this.inPlay = true;
        this.equipmentCount = 0;
        this.equipment = null;
        if (team == deadId && type != 4) {
            this.verticalMove = 1;
            this.horizontalMove = 1;
            this.diagonalMove = 0;
        } else {
            this.verticalMove = verticalMove;
            this.horizontalMove = horizontalMove;
            this.diagonalMove = diagonalMove;
        }
    }

    public void enteredSafeHaven() {
        inSafeHaven = true;
    }

    public String[] getCreatureInfo() {
        String[] info = new String[7];
        info[0] = Integer.toString(id);
        switch (type) {
            case 0 ->
                info[1] = "Criança";
            case 1 ->
                info[1] = "Adulto";
            case 2 ->
                info[1] = "Idoso";
            case 3 ->
                info[1] = "Cão";
            case 4 ->
                    info[1] = "Vampiro";
            default ->
                info[1] = "";
        }
        if (team != originalTeam) {
            info[2] = "Zombie (Transformado)";
        } else {
            if (team == aliveId) {
                info[2] = "Humano";
            } else {
                info[2] = "Zombie";
            }
        }
        info[3] = name;
        if (inSafeHaven) {
            info[4] = null;
            info[5] = null;
        } else {
            info[4] = Integer.toString(column);
            info[5] = Integer.toString(line);
        }
        info[6] = null;
        return info;
    }

    public int getOriginalTeam() {
        return originalTeam;
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

    public int getType() {
        return type;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void removeEquipment() {
        equipment = null;
        equipmentCount--;
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

    public boolean verifyMove(int column0, int line0, int column1, int line1) {
        return (Math.abs(column0 - column1) <= horizontalMove && (line0 == line1)) || (column0 == column1 && Math.abs(line0 - line1) <= verticalMove) || (Math.abs(column0 - column1) <= diagonalMove && Math.abs(line0 - line1) <= diagonalMove && Math.abs(line0 - line1) == Math.abs(column0 - column1));
    }

    public int getAliveId() {
        return aliveId;
    }

    public int getDeadId() {
        return deadId;
    }

    public boolean shoots() {
        return equipment.shoots();
    }

    public boolean usesBleach() {
        return equipment.bleachCanBeUsed();
    }

    public boolean doesntHaveEquipment() {
        return equipment == null;
    }

    public void turnsIntoZombie() {
        this.team = deadId;
        this.verticalMove = 1;
        this.horizontalMove = 1;
        this.diagonalMove = 0;
    }

    @Override
    public String toString() {
        if (getTeam() == aliveId) {
            if (equipment != null) {
                return id + " | Humano | " + name + " | +" + equipmentCount + " @ (" + column + ", " + line + ")";
            }
            return id + " | Humano | " + name + " | +" + equipmentCount + " @ (" + column + ", " + line + ")";
        } else if (team == deadId) {
            return id + " | Zombie | " + name + " | -" + equipmentCount + " @ (" + column + ", " + line + ")";
        }
        return "";
    }
}
