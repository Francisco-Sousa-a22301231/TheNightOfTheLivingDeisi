package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class Elder extends Character{

    public Elder(int id, int team, String name, int column, int line, int aliveId, int deadId) {
        super(id, team, 2, name, column, line, 0, 0, 1, aliveId, deadId);
    }

    @Override
    public String toString() {
        if (getTeam() == getAliveId()) {
            return getId() + " | Idoso | Humano | " + getName() + " | +" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        } else if (getTeam() == getDeadId()) {
            return getId() + " | Idoso | Zombie | " + getName() + " | -" + equipmentCount + " @ (" + getColumn() + ", " + getLine() + ")";
        }
        return "";
    }
}
