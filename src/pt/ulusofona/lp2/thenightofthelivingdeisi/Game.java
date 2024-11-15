package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private int[][] board;
    private int columns;
    private int lines;
    private int aliveId;
    private int deadId;
    private int numberOfPlays;
    private int currentTeam;
    private int initialTeam;
    private boolean isDay;
    private int movesToChangeDay;
    private HashMap<Integer,Character> characters;
    private HashMap<Integer,Equipment> equipments;
    private ArrayList<SafeHeaven> safeHeavens = new ArrayList<>();

    public Game(int columns, int lines, int initialTeam, int aliveId, int deadId, HashMap<Integer,Character> characters, HashMap<Integer,Equipment> equipments, ArrayList<SafeHeaven> safeHeavens) {
        this.columns = columns;
        this.lines = lines;
        this.characters = characters;
        this.equipments = equipments;
        this.safeHeavens = safeHeavens;
        this.isDay = true;
        this.numberOfPlays = 0;
        this.movesToChangeDay = 1;
        this.initialTeam = initialTeam;
        this.currentTeam = initialTeam;
        this.aliveId = aliveId;
        this.deadId = deadId;
        this.board = new int[columns][lines];
        setUpCharacters();
        setUpEquipments();
        setUpSafeHeavens();

    }

    public int getInitialTeam() {
        return initialTeam;
    }

    public void setUpCharacters() {
        for (Character c : characters.values()) {
            board[c.getColumn()][c.getLine()] = c.getId();
        }
    }

    public void setUpEquipments() {
        for (Equipment e : equipments.values()) {
            board[e.getColumn()][e.getLine()] = e.getId();
        }
    }

    public void setUpSafeHeavens() {
        for (SafeHeaven sh : safeHeavens) {
            board[sh.getColumn()][sh.getLine()] = sh.getId();
        }
    }

    public void changeTime() {
        isDay = !isDay;
    }

    public void changeTeam() {
        if (currentTeam == aliveId) {
            currentTeam = deadId;
        } else {
            currentTeam = aliveId;
        }
    }

    public String getSquareInfo(int column, int line) {
        if (column > getColumns() || line > getLines()) {
            return "";
        }
        int id = searchCoordinates(column, line);
        if (id == 0) {
            return "";
        } else if (id > 0 && id != 1000) {
            Character c = getCharacter(id);
            if (c.getTeam() == aliveId) {
                return "H:" + id;
            } else if (c.getTeam() == deadId) {
                return "Z:" + id;
            }
        } else if (id < 0) {
            return "E:" + id;
        } else {
            return "SH";
        }
        return "";
    }

    public void addNewPlay() {
        changeTeam();
        numberOfPlays++;
        if (movesToChangeDay == 0) {
            changeTime();
            movesToChangeDay = 1;
        } else {
            movesToChangeDay--;
        }
    }

    private boolean elderCantMove(Character character) {
        return character.getTeam() == aliveId && !isDay;
    }

    private boolean vampireCantMove(Character character) {
        return isDay;
    }


    public boolean move(int column0, int line0, int column1, int line1) {
        int id0 = searchCoordinates(column0, line0);
        int id1 = searchCoordinates(column1, line1);

        if (!isMoveValid(id0, id1)) {
            return false;
        }

        Character c0 = getCharacter(id0);
        Character c1 = getCharacter(id1);

        if (!canCharacterMove(c0, column0, line0, column1, line1)) {
            return false;
        }

        if (c1 == null) {
            handleEmptyDestination(c0, id0, id1, column0, line0, column1, line1);
        } else {
            handleCombat(c0, c1, id0, column0, line0, column1, line1);
        }

        handleElderEquipment(c0);

        addNewPlay();
        return true;
    }

    private boolean isMoveValid(int id0, int id1) {
        return id0 > 0 && (id0 != 0 || id1 != 0) && getCharacter(id0).getTeam() == currentTeam;
    }

    private boolean canCharacterMove(Character c0, int column0, int line0, int column1, int line1) {
        return !(c0.getType() == 2 && elderCantMove(c0)) &&
                !(c0.getType() == 4 && vampireCantMove(c0)) &&
                c0.verifyMove(column0, line0, column1, line1);
    }

    private void handleEmptyDestination(Character c0, int id0, int id1, int column0, int line0, int column1, int line1) {
        board[column1][line1] = id0;
        c0.changeCoordinates(column1, line1);
        removeFromCoordinates(column0, line0);

        if (id1 < 0) {
            Equipment e = getEquipment(id1);
            if (c0.getTeam() == aliveId && canPickUpEquipment(c0, e)) {
                c0.pickUpEquipment(e);
            } else if (c0.getTeam() == deadId) {
                c0.destroyEquipment(e);
            }
        }
    }

    private boolean canPickUpEquipment(Character c0, Equipment e) {
        return c0.getType() != 0 || !e.isOffensive();
    }

    private void handleCombat(Character c0, Character c1, int id0, int column0, int line0, int column1, int line1) {
        if (c0.getTeam() == aliveId && c1.getTeam() == deadId) {
            handleAliveVsDeadCombat(c0, c1, id0, column0, line0, column1, line1);
        } else if (c0.getTeam() == deadId && c1.getTeam() == aliveId) {
            handleDeadVsAliveCombat(c0, c1, column0, line0);
        }
    }

    private void handleAliveVsDeadCombat(Character c0, Character c1, int id0, int column0, int line0, int column1, int line1) {
        if (c0.hasEquipment(0) || c0.hasEquipment(3) || c0.doesntHaveEquipment()) {
            return;
        }

        if ((c0.hasEquipment(2) && c0.shoots()) || c0.hasEquipment(1)) {
            board[column1][line1] = id0;
            c0.changeCoordinates(column1, line1);
            removeFromCoordinates(column0, line0);
            characters.remove(c1.getId());
        }
    }

    private void handleDeadVsAliveCombat(Character c0, Character c1, int column0, int line0) {
        if (c1.hasEquipment(0) || (c1.hasEquipment(3) && c1.usesBleach())) {
            addNewPlay();
        } else if ((c1.hasEquipment(1) || c1.hasEquipment(2)) && c1.shoots()) {
            removeFromCoordinates(column0, line0);
            characters.remove(c0.getId());
        } else {
            c1.turnsIntoZombie();
        }
    }

    private void handleElderEquipment(Character c0) {
        if (c0.getTeam() == aliveId && c0.getType() == 2 && c0.getEquipment() != null) {
            Equipment e = c0.getEquipment();
            if (c0.getColumn() != e.getColumn() || c0.getLine() != e.getLine()) {
                board[e.getColumn()][e.getLine()] = e.getId();
                c0.removeEquipment();
            }
        }
    }





    public void removeFromCoordinates(int column, int line) {
        board[column][line] = 0;
    }

    public int searchCoordinates(int columns, int lines) {
        return board[columns][lines];
    }

    public Character getCharacter(int id) {
        return characters.get(id) == null? null : characters.get(id);
    }

    public Equipment getEquipment(int id) {
        return equipments.get(id) == null? null : equipments.get(id);
    }

    public boolean isDay() {
        return isDay;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public int getCurrentTeam() {
        return currentTeam;
    }

    public int getColumns() {
        return columns;
    }

    public int getLines() {
        return lines;
    }

    public HashMap<Integer, ArrayList<Character>> getSurvivorsAndOthers() {
        HashMap<Integer, ArrayList<Character>> players = new HashMap<>();
        ArrayList<Character> survivors = new ArrayList<>();
        ArrayList<Character> others = new ArrayList<>();
        for (Character c : characters.values()) {
            if (c.getTeam() == aliveId) {
                survivors.add(c);
            } else if (c.getTeam() == deadId){
                others.add(c);
            }
        }
        players.put(0, survivors); // todo
        players.put(1, others);
        return players;
    }
}
