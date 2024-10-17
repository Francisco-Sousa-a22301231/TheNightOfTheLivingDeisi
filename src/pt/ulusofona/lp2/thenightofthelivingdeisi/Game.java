package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private int[][] board;
    private int columns;
    private int lines;
    private int numberOfPlays;
    private int currentTeam;
    private int initialTeam;
    private boolean isDay;
    private int movesToChangeDay;
    private HashMap<Integer,Character> characters;
    private HashMap<Integer,Equipment> equipments;

    public Game(int columns, int lines, int initialTeam, HashMap<Integer,Character> characters, HashMap<Integer,Equipment> equipments) {
        this.columns = columns;
        this.lines = lines;
        this.characters = characters;
        this.equipments = equipments;
        this.isDay = true;
        this.numberOfPlays = 0;
        this.movesToChangeDay = 1;
        this.initialTeam = initialTeam;
        this.currentTeam = initialTeam;
        this.board = new int[columns][lines];
        setUpCharacters();
        setUpEquipments();
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

    public void changeTime() {
        isDay = !isDay;
    }

    public void changeTeam() {
        if (currentTeam == 1) {
            currentTeam = 0;
        } else {
            currentTeam = 1;
        }
    }

    public String getSquareInfo(int column, int line) {
        if (column > getColumns() || line > getLines()) {
            return "";
        }
        int id = searchCoordinates(column, line);
        if (id == 0) {
            return "";
        } else if (id > 0) {
            Character c = getCharacter(id);
            if (c.getTeam() == 1) {
                return "H:" + id;
            } else {
                return "Z:" + id;
            }
        } else {
            return "E:" + id;
        }
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

    public boolean verifyMove(int column0, int line0, int column1, int line1) {
        return (Math.abs(column0 - column1) == 1 && (line0 == line1)) || (column0 == column1 && Math.abs(line0 - line1) == 1);
    }

    public boolean move(int column0, int line0, int column1, int line1) {
        int id0 = searchCoordinates(column0,line0);
        int id1 = searchCoordinates(column1,line1);
        if (id0 <= 0 || getCharacter(id0).getTeam() != currentTeam) {
            return false;
        } else if (id1 <= 0 && verifyMove(column0, line0, column1, line1)) {
            Character c0 = getCharacter(id0);
            board[column1][line1] = id0;
            c0.changeCoordinates(column1, line1);
            removeFromCoordinates(column0, line0);
            if (id1 < 0) {
                Equipment e = getEquipment(id1);
                if (c0.getTeam() == 1) {
                    c0.pickUpEquipment(e);
                } else {
                    c0.destroyEquipment(e);
                }
            }
            addNewPlay();
            return true;
        }
        return false;
    }

    public void removeFromCoordinates(int x, int y) {
        board[x][y] = 0;
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
            if (c.getTeam() == 0) {
                survivors.add(c);
            } else {
                others.add(c);
            }
        }
        players.put(0, survivors);
        players.put(1, others);
        return players;
    }
}
