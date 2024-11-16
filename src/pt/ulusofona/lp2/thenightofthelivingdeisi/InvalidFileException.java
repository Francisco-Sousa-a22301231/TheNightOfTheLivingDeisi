package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class InvalidFileException extends Exception {
    private int line;

    public InvalidFileException(int line) {
        this.line = line;
    }

    public int getLineWithError() {
        return line;
    }


}
