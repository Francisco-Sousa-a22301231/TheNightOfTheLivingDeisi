package pt.ulusofona.lp2.thenightofthelivingdeisi;

public class InvalidFileException extends Exception {
    private int line;
    private int arguments;

    public InvalidFileException(int line, int arguments) {
        this.line = line + 1;
        this.arguments = arguments;
    }

    public int getLineWithError() {
        return line;
    }


}
