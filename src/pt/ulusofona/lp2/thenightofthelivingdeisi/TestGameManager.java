package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameManager {
    @Test public void testGame_5x5_1H_1Z_2E() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/5x5_1H_1Z_2E.txt"));
        Game game = gameManager.getGame();
        assertEquals(5, game.getColumns());
        assertEquals(5, game.getLines());
        assertEquals(1, gameManager.getInitialTeamId());
        assertTrue(game.isDay());
        assertEquals("Freddy M.", game.getCharacter(1).getName());
        assertEquals(4, game.getCharacter(2).getColumn());
        assertEquals(4, game.getCharacter(2).getLine());
        assertEquals(-1, game.searchCoordinates(1,0));
        assertEquals(-2, game.searchCoordinates(4,3));
        assertEquals("Paciente Zero", game.getCharacter(game.searchCoordinates(4,4)).getName());
    }

    @Test public void testGame_5x6_3H_2Z_1E() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/5x6_3H_2Z_1E.txt"));
        Game game = gameManager.getGame();
        assertEquals(6, game.getColumns());
        assertEquals(5, game.getLines());
        assertEquals("", gameManager.getSquareInfo(0,0));
        assertEquals("Z:2", gameManager.getSquareInfo(2,2));
        assertEquals("H:4", gameManager.getSquareInfo(5,4));
        assertEquals("E:-1", gameManager.getSquareInfo(0,4));
        assertEquals("", gameManager.getSquareInfo(0,5));
    }
}
