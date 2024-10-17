package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
        String[] result = new String[5];
        result[0] = "-1";
        result[1] = "0";
        result[2] = "1";
        result[3] = "0";
        result[4] = null;
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getEquipmentInfo(-1)));
        assertEquals("-1 | Escudo de madeira @ (1,0)", gameManager.getEquipmentInfoAsString(-1));
        assertEquals("E:-1", gameManager.getSquareInfo(1,0));
        result = new String[5];
        result[0] = "-2";
        result[1] = "1";
        result[2] = "4";
        result[3] = "3";
        result[4] = null;
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getEquipmentInfo(-2)));
        assertEquals("-2 | Espada samurai @ (4,3)", gameManager.getEquipmentInfoAsString(-2));
        assertEquals("E:-2", gameManager.getSquareInfo(4,3));
    }

    @Test public void testGame_5x6_3H_2Z_1E() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/5x6_3H_2Z_1E.txt"));
        Game game = gameManager.getGame();
        assertEquals(6, game.getColumns());
        assertEquals(5, game.getLines());
        assertEquals("", gameManager.getSquareInfo(0,0));
        assertEquals(Arrays.toString(new String[0]), Arrays.toString(gameManager.getCreatureInfo(0)));
        assertEquals("", gameManager.getCreatureInfoAsString(0));
        assertEquals("", gameManager.getEquipmentInfoAsString(0));
        assertEquals(Arrays.toString(new String[0]), Arrays.toString(gameManager.getEquipmentInfo(0)));
        String[] result = new String[6];
        result[0] = "2";
        result[1] = "Humano";
        result[2] = "Freddy M.";
        result[3] = "2";
        result[4] = "2";
        result[5] = null;
        assertEquals("H:2", gameManager.getSquareInfo(2,2));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(2)));
        assertEquals("2 | Humano | Freddy M. | +0 @ (2,2)", gameManager.getCreatureInfoAsString(2));
        result = new String[6];
        result[0] = "4";
        result[1] = "Zombie";
        result[2] = "Ozzy Osborne";
        result[3] = "5";
        result[4] = "4";
        result[5] = null;
        assertEquals("Z:4", gameManager.getSquareInfo(5,4));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(4)));
        assertEquals("4 | Zombie | Ozzy Osborne | -0 @ (5,4)", gameManager.getCreatureInfoAsString(4));
        result = new String[5];
        result[0] = "-1";
        result[1] = "0";
        result[2] = "0";
        result[3] = "4";
        result[4] = null;
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getEquipmentInfo(-1)));
        assertEquals("-1 | Escudo de madeira @ (0,4)", gameManager.getEquipmentInfoAsString(-1));
        assertEquals("E:-1", gameManager.getSquareInfo(0,4));
        assertEquals("", gameManager.getSquareInfo(0,5));
        assertEquals("", gameManager.getSquareInfo(7,8));
    }

    @Test public void testGame_nofile() {
        GameManager gameManager = new GameManager();
        boolean result = gameManager.loadGame(new File("test-files/0_3H_2Z_1E.txt"));
        assertFalse(result);
    }

    @Test public void testGetWorldSize() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/5x6_3H_2Z_1E.txt"));
        int[] worldSize = gameManager.getWorldSize();
        assertEquals(5, worldSize[0]);
        assertEquals(6, worldSize[1]);
    }

    @Test public void testGetCurrentTeam() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/5x6_3H_2Z_1E.txt"));
        int team = gameManager.getCurrentTeamId();
        assertEquals(1, team);
    }

    @Test public void testIsDay() {
        GameManager gameManager = new GameManager();
        boolean day = gameManager.isDay();
        assertTrue(day);
        gameManager.loadGame(new File("test-files/5x6_3H_2Z_1E.txt"));
        day = gameManager.isDay();
        assertTrue(day);
    }
}
