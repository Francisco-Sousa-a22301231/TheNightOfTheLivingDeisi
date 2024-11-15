package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class SafeHaven {
    private final int id = 1000;
    private final int column;
    private final int line;
    private ArrayList<Character> characters = new ArrayList<>();

    public SafeHaven(int column, int line) {
        this.column = column;
        this.line = line;
    }

    public void addEntry(Character character) {
        characters.add(character);
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public int getColumn() {
        return column;
    }

    public int getLine() {
        return line;
    }

    public int getId() {
        return id;
    }
}
