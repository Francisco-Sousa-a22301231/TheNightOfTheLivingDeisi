package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameManager {
    private Game game;
    private File file;

    public GameManager() {
    }

    public Game getGame() {
        return game;
    }

    public boolean loadGame(File file) {
        this.file = file;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            String[] parts;
            int lineNumber = 1;
            int columns = 0;
            int lines = 0;
            int startingTeamId = 0;
            int numberOfCharacters = 0;
            int numberOfEquipments = 0;
            HashMap<Integer,Character> characters = new HashMap<>();
            HashMap<Integer,Equipment> equipments = new HashMap<>();
            while ((line = br.readLine()) != null) {
                if (lineNumber == 1) {
                    parts = line.split(" ");
                    lines = Integer.parseInt(parts[0]);
                    columns = Integer.parseInt(parts[1]);
                } else if (lineNumber == 2) {
                    startingTeamId = Integer.parseInt(line);
                } else if (lineNumber == 3) {
                    numberOfCharacters = Integer.parseInt(line);
                } else if (lineNumber == 4) {
                    while (lineNumber < (4 + numberOfCharacters)) {
                        parts = line.split(" : ");
                        characters.put(Integer.parseInt(parts[0]), new Character(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                        lineNumber++;
                        line = br.readLine();
                    }
                    numberOfEquipments = Integer.parseInt(line);
                    lineNumber++;
                    line = br.readLine();
                    while (lineNumber < (5 + numberOfCharacters + numberOfEquipments)) {
                        parts = line.split(" : ");
                        equipments.put(Integer.parseInt(parts[0]), new Equipment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                        lineNumber++;
                        line = br.readLine();
                    }
                }
                lineNumber++;
            }
            game = new Game(columns, lines, startingTeamId, characters, equipments);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getInitialTeamId() {
        return game.getInitialTeam();
    }

    public int[] getWorldSize() {
        return new int[]{game.getLines(), game.getColumns()};
    }

    public int getCurrentTeamId() {
        return game.getCurrentTeam();
    }

    public boolean isDay() {
        if (game == null) {
            return true;
        }
        return game.isDay();
    }

    public String getSquareInfo(int x, int y) {
        if (x >= game.getColumns() || y >= game.getLines()) {
            return "";
        }
        return game.getSquareInfo(x, y);
    }

    public String[] getCreatureInfo(int id) {
        Character character = game.getCharacter(id);
        if (character!= null) {
            return character.getCreatureInfo();
        }
       return new String[0];
    }

    public String[] getEquipmentInfo(int id) {
        Equipment equipment = game.getEquipment(id);
        if (equipment!= null) {
            return equipment.getEquipmentInfo();
        }
        return new String[0];
    }

    public String getCreatureInfoAsString(int id) {
        Character character = game.getCharacter(id);
        if (character!= null) {
            return character.toString();
        }
        return "";
    }

    public String getEquipmentInfoAsString(int id) {
        Equipment equipment = game.getEquipment(id);
        if (equipment!= null) {
            return equipment.toString();
        }
        return "";
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        Character character = game.getCharacter(creatureId);
        if (character!= null) {
            return character.hasEquipment(equipmentTypeId);
        }
        return false;
    }

    public boolean move(int column0, int line0, int column1, int line1) {
        return game.move(column0, line0, column1, line1);
    }

    public boolean gameIsOver() {
        return game.getNumberOfPlays() == 12;
    }

    public ArrayList<String> getSurvivors() {
        ArrayList<String> survivors = new ArrayList<>();
        HashMap<Integer,ArrayList<Character>> players = game.getSurvivorsAndOthers();
        survivors.add("Nr. de turnos terminados:");
        survivors.add("12");
        survivors.add(" ");
        survivors.add("OS VIVOS");
        for (Character c : players.get(0)) {
            survivors.add(c.getId() + " " + c.getName());
        }
        survivors.add(" ");
        survivors.add("OS OUTROS");
        for (Character c : players.get(1)) {
            survivors.add(c.getId() + " " + c.getName());
        }
        survivors.add("-----");
        return survivors;
    }

    public JPanel getCreditsPanel() {
        return null;
    }

    public HashMap<String, String> customizeBoard() {
        return null;
    }
}
