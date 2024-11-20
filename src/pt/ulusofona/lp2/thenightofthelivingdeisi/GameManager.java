package pt.ulusofona.lp2.thenightofthelivingdeisi;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GameManager {
    private Game game;
    private File file;
    private int aliveId = 20;
    private int deadId = 10;


    public GameManager() {
    }

    public Game getGame() {
        return game;
    }

    public void loadGame(File file) throws InvalidFileException, FileNotFoundException {
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
            int numberOfSafeHeavens = 0;
            HashMap<Integer, Character> characters = new HashMap<>();
            HashMap<Integer, Equipment> equipments = new HashMap<>();
            ArrayList<SafeHaven> safeHavens = new ArrayList<>();
            ArrayList<String> moves = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    throw new InvalidFileException(lineNumber);
                }
                if (lineNumber == 1) {
                    parts = line.split(" ");

                    if (parts[0] == null || parts[1] == null || parts.length != 2) {
                        throw new InvalidFileException(lineNumber);
                    }
                    lines = Integer.parseInt(parts[0]);
                    columns = Integer.parseInt(parts[1]);
                } else if (lineNumber == 2) {
                    startingTeamId = Integer.parseInt(line);
                    if (startingTeamId != aliveId && startingTeamId != deadId ) {
                        throw new InvalidFileException(lineNumber);
                    }
                } else if (lineNumber == 3) {
                    numberOfCharacters = Integer.parseInt(line);
                    if (numberOfCharacters > columns * lines) {
                        throw new InvalidFileException(lineNumber);
                    }
                } else if (lineNumber == 4) {
                    while (lineNumber < (4 + numberOfCharacters)) {
                        parts = line.split(" : ");
                        for (String part : parts) {
                            if (part.isEmpty() || Objects.equals(part, "") || parts.length != 6) {
                                throw new InvalidFileException(lineNumber);
                            }
                        }
                        characters.put(Integer.parseInt(parts[0]),
                                newCharacter(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                                        parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5])));
                        lineNumber++;
                        line = br.readLine();
                    }
                    numberOfEquipments = Integer.parseInt(line);
                    lineNumber++;
                    line = br.readLine();
                    while (lineNumber < (5 + numberOfCharacters + numberOfEquipments)) {
                        parts = line.split(" : ");
                        for (String part : parts) {
                            if (part.isEmpty() || Objects.equals(part, "") || parts.length != 4) {
                                throw new InvalidFileException(lineNumber);
                            }
                        }
                        equipments.put(Integer.parseInt(parts[0]),
                                newEquipment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                                        Integer.parseInt(parts[3])));
                        lineNumber++;
                        line = br.readLine();
                    }
                    if (line != null) {
                        numberOfSafeHeavens = Integer.parseInt(line);
                        lineNumber++;
                        line = br.readLine();
                    }
                    while (line != null && (lineNumber < (6 + numberOfCharacters + numberOfEquipments + numberOfSafeHeavens))) {
                        parts = line.split(" : ");
                        for (String part : parts) {
                            if (part.isEmpty() || Objects.equals(part, "") || parts.length != 2) {
                                throw new InvalidFileException(lineNumber);
                            }
                        }
                        safeHavens.add(new SafeHaven(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                        lineNumber++;
                        line = br.readLine();
                    }
                    if (line != null && !line.trim().isEmpty()) {
                        moves.add(line);
                        while ((line = br.readLine()) != null) {
                            moves.add(line);
                        }
                    }
                }
                lineNumber++;
            }

            game = new Game(columns, lines, startingTeamId, aliveId, deadId, characters, equipments, safeHavens, moves);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Character newCharacter(int id, int team, int type, String name, int column, int line) {
        return switch (type) {
            case 0 -> new Child(id, team, name, column, line, aliveId, deadId);
            case 1 -> new Adult(id, team, name, column, line, aliveId, deadId);
            case 2 -> new Elder(id, team, name, column, line, aliveId, deadId);
            case 3 -> new Dog(id, name, column, line, aliveId, deadId);
            case 4 -> new Vampire(id, name, column, line, aliveId, deadId);
            default -> null;
        };
    }

    private Equipment newEquipment(int id, int type, int column, int line) {
        return switch (type) {
            case 0 -> new Shield(id, type, column, line);
            case 1 -> new Sword(id, type, column, line);
            case 2 -> new Pistol(id, type, column, line);
            case 3 -> new Bleach(id, type, column, line);
            default -> null;
        };
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

    public String getSquareInfo(int column, int line) {
        if (column >= game.getColumns() || line >= game.getLines()) {
            return null;
        }
        return game.getSquareInfo(column, line);
    }

    public String[] getCreatureInfo(int id) {
        Character character = game.getCharacter(id);
        if (character != null) {
            return character.getCreatureInfo();
        }
        return null;
    }

    public String[] getEquipmentInfo(int id) {
        Equipment equipment = game.getEquipment(id);
        if (equipment != null) {
            return equipment.getEquipmentInfo();
        }
        return null;
    }

    public String getCreatureInfoAsString(int id) {
        Character character = game.getCharacter(id);
        if (character != null) {
            return character.toString();
        }
        return null;
    }

    public String getEquipmentInfoAsString(int id) {
        Equipment equipment = game.getEquipment(id);
        if (equipment!= null) {
            return equipment.toString();
        }
        return null;
    }

    public boolean hasEquipment(int creatureId, int equipmentTypeId) {
        Character character = game.getCharacter(creatureId);
        if (character!= null) {
            return character.hasEquipment(equipmentTypeId);
        }
        return false;
    }

    public boolean move(int column0, int line0, int column1, int line1) {
        boolean result = game.move(column0, line0, column1, line1);
        return result;
    }

    public List<Integer> getIdsInSafeHaven() {

        return game.getIdsInSafeHaven();
    }

    public void saveGame(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            ArrayList<String> moves = game.getMoves();

            for (int i = 0; i < moves.size(); i++) {
                bw.write(moves.get(i));
                if (i < moves.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public boolean gameIsOver() {
        return game.getBoringMoveCount() >= 8 || game.onlyOneTeamRemaining();
    }

    public ArrayList<String> getSurvivors() {
        ArrayList<String> survivors = new ArrayList<>();
        HashMap<Integer,ArrayList<Character>> players = game.getSurvivorsAndOthers();
        survivors.add("Nr. de turnos terminados:");
        survivors.add("" + game.getNumberOfPlays());
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
