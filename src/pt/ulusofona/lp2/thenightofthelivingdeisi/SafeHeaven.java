package pt.ulusofona.lp2.thenightofthelivingdeisi;

import java.util.ArrayList;

public class SafeHeaven {
    private final int id = 1000;
    private final int column;
    private final int line;
    private ArrayList<Character> characters = new ArrayList<>();

    public SafeHeaven(int column, int line) {
        this.column = column;
        this.line = line;
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
