package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Child extends Character{

    public Child(int id, int team, String name, int column, int line, int aliveId, int deadId) {
        super(id, team, 0, name, column, line, 1, 1, 0, aliveId, deadId);
    }

    @Override
    public String toString() {
        if (isInSafeHaven()) {
            return getId() + " | Criança | Humano | " + getName() + " | +" + equipmentCount + " @ Safe Haven";
        }
        if (getTeam() != getOriginalTeam()) {
            return getId() + " | Criança | Zombie (Transformado) | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        }
        if (getTeam() == getAliveId()) {
            if (getEquipment() != null) {
                return getId() + " | Criança | Humano | " + getName() + " | +" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ") | "  + getEquipment().getEquipmentInfoAsString();
            }
            return getId() + " | Criança | Humano | " + getName() + " | +" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        } else if (getTeam() == getDeadId()) {
            return getId() + " | Criança | Zombie | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        }
        return "";
    }
}
