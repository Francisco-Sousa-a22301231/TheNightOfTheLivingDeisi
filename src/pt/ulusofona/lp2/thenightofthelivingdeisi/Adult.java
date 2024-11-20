package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Adult extends Character{

    public Adult(int id, int team , String name, int column, int line, int aliveId, int deadId) {
        super(id, team, 1, name, column, line, 2, 2, 2, aliveId, deadId);
    }

    @Override
    public String toString() {
        if (getTeam() != getOriginalTeam()) {
            return getId() + " | Adulto | Zombie (Transformado) | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        }
        if (getTeam() == getAliveId()) {
            if (getEquipment() != null) {
                return getId() + " | Adulto | Humano | " + getName() + " | +" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ") | "  + getEquipment().getEquipmentInfoAsString();
            }
            return getId() + " | Adulto | Humano | " + getName() + " | +" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        } else if (getTeam() == getDeadId()) {
            return getId() + " | Adulto | Zombie | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        }
        return "";
    }
}
