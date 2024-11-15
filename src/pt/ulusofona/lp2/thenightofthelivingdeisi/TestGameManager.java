package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {

    @Test public void testGame_7x7_5H_5Z_4E_2SH() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        Game game = gameManager.getGame();
        assertEquals(7, game.getColumns());
        assertEquals(7, game.getLines());
        assertEquals(10, gameManager.getInitialTeamId());
        assertTrue(game.isDay());
        assertEquals("Melanie", game.getCharacter(1).getName());
        assertEquals(5, game.getCharacter(2).getColumn());
        assertEquals(3, game.getCharacter(2).getLine());
        assertEquals(-1, game.searchCoordinates(6,3));
        assertEquals(-2, game.searchCoordinates(2,0));
        assertEquals(1000, game.searchCoordinates(0,6));
        assertEquals(1000, game.searchCoordinates(6,0));
        assertEquals("Freddie M.", game.getCharacter(game.searchCoordinates(4,3)).getName());
        String[] result = new String[5];
        result[0] = "-1";
        result[1] = "0";
        result[2] = "6";
        result[3] = "3";
        result[4] = null;
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getEquipmentInfo(-1)));
        assertEquals(0, game.getEquipment(-1).getTipo());
        assertEquals("-1 | Escudo de madeira @ (6,3)", gameManager.getEquipmentInfoAsString(-1));
        assertEquals("E:-1", gameManager.getSquareInfo(6,3));
        assertEquals("SH", gameManager.getSquareInfo(6,0));
        assertEquals("SH", gameManager.getSquareInfo(0,6));
        result = new String[5];
        result[0] = "-2";
        result[1] = "1";
        result[2] = "2";
        result[3] = "0";
        result[4] = null;
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getEquipmentInfo(-2)));
        assertEquals("-2 | Espada samurai @ (2,0)", gameManager.getEquipmentInfoAsString(-2));
        assertEquals("E:-2", gameManager.getSquareInfo(2,0));
    }

    @Test public void testGameCreatureInfo() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        Game game = gameManager.getGame();
        assertEquals("", gameManager.getSquareInfo(0,0));
        assertEquals(Arrays.toString(new String[0]), Arrays.toString(gameManager.getCreatureInfo(0)));
        assertEquals("", gameManager.getCreatureInfoAsString(0));
        assertEquals("", gameManager.getEquipmentInfoAsString(0));
        assertEquals(Arrays.toString(new String[0]), Arrays.toString(gameManager.getEquipmentInfo(0)));
        String[] result = new String[7];
        result[0] = "8";
        result[1] = "Idoso";
        result[2] = "Humano";
        result[3] = "James Bond";
        result[4] = "5";
        result[5] = "6";
        result[6] = null;
        assertEquals("H:8", gameManager.getSquareInfo(5,6));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(8)));
        result = new String[7];
        result[0] = "1";
        result[1] = "Criança";
        result[2] = "Zombie";
        result[3] = "Melanie";
        result[4] = "3";
        result[5] = "3";
        result[6] = null;
        assertEquals("Z:1", gameManager.getSquareInfo(3,3));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(1)));
        result = new String[7];
        result[0] = "2";
        result[1] = "Adulto";
        result[2] = "Zombie";
        result[3] = "Walker";
        result[4] = "5";
        result[5] = "3";
        result[6] = null;
        assertEquals("Z:2", gameManager.getSquareInfo(5,3));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(2)));
        result = new String[7];
        result[0] = "3";
        result[1] = "Idoso";
        result[2] = "Zombie";
        result[3] = "Frankenstein";
        result[4] = "4";
        result[5] = "5";
        result[6] = null;
        assertEquals("Z:3", gameManager.getSquareInfo(4,5));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(3)));
        result = new String[7];
        result[0] = "4";
        result[1] = "Vampiro";
        result[2] = "Crawler";
        result[3] = "0";
        result[4] = "1";
        result[5] = null;
        assertEquals("Z:4", gameManager.getSquareInfo(0,1));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(4)));
        result = new String[7];
        result[0] = "10";
        result[1] = "Cão";
        result[2] = "Max";
        result[3] = "2";
        result[4] = "2";
        result[5] = null;
        assertEquals("H:10", gameManager.getSquareInfo(2,2));
        assertEquals(Arrays.toString(result), Arrays.toString(gameManager.getCreatureInfo(10)));
        assertEquals("1 | Criança | Zombie | Melanie | -0 @ (3, 3)", gameManager.getCreatureInfoAsString(1));
        assertEquals("2 | Adulto | Zombie | Walker | -0 @ (5, 3)", gameManager.getCreatureInfoAsString(2));
        assertEquals("3 | Idoso | Zombie | Frankenstein | -0 @ (4, 5)", gameManager.getCreatureInfoAsString(3));
        assertEquals("4 | Vampiro | Crawler | -0 @ (0, 1)", gameManager.getCreatureInfoAsString(4));
        assertEquals("5 | Criança | Zombie | Babe | -0 @ (1, 1)", gameManager.getCreatureInfoAsString(5));
        assertEquals("6 | Criança | Humano | Karate Kid | +0 @ (3, 4)", gameManager.getCreatureInfoAsString(6));
        assertEquals("7 | Adulto | Humano | Freddie M. | +0 @ (4, 3)", gameManager.getCreatureInfoAsString(7));
        assertEquals("8 | Idoso | Humano | James Bond | +0 @ (5, 6)", gameManager.getCreatureInfoAsString(8));
        assertEquals("9 | Adulto | Humano | John Wayne | +0 @ (6, 5)", gameManager.getCreatureInfoAsString(9));
        assertEquals("10 | Cão | Max @ (2, 2)", gameManager.getCreatureInfoAsString(10));
        assertEquals("", gameManager.getSquareInfo(0,5));
        assertEquals("", gameManager.getSquareInfo(7,8));
        assertFalse( gameManager.hasEquipment(3,3));
        assertFalse( gameManager.hasEquipment(4,3));
        assertFalse( gameManager.gameIsOver());
    }

    @Test public void testGame_nofile() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/0_3H_2Z_1E.txt"));
    }

    @Test public void testGetWorldSize() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        int[] worldSize = gameManager.getWorldSize();
        assertEquals(7, worldSize[0]);
        assertEquals(7, worldSize[1]);
    }

    @Test public void testGetCurrentTeam() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        int team = gameManager.getCurrentTeamId();
        assertEquals(10, team);
    }



    @Test public void testMove() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        assertEquals(1, gameManager.getGame().searchCoordinates(3,3));
        assertTrue(gameManager.isDay());
        assertFalse(gameManager.move(3,3,3,1)); // dead tries to moves | 1
        assertTrue(gameManager.move(3,3,3,2)); // dead moves | 1
        assertTrue(gameManager.isDay());
        assertEquals(6, gameManager.getGame().searchCoordinates(3,4));
        assertEquals(7, gameManager.getGame().searchCoordinates(4,3));
        assertFalse(gameManager.move(3,4,1,4)); // alive child tries to move
        assertFalse(gameManager.move(3,4,3,6)); // alive child tries to move
        assertFalse(gameManager.move(3,4,2,5)); // alive child tries to move
        assertTrue(gameManager.move(6,5,6,4)); // alive moves 2
        assertFalse(gameManager.isDay());
        assertFalse(gameManager.move(1,1,1,3)); // dead tries to move too much
        assertFalse(gameManager.move(1,1,0,2)); // dead tries to move too much
        assertTrue(gameManager.move(1,1,1,0)); // dead moves | 3
        assertFalse(gameManager.isDay());
        assertFalse(gameManager.move(5,6,6,5)); // alive elder tries to move
        assertFalse(gameManager.move(0,0,0,1)); // no one is there
        assertTrue(gameManager.move(4,3,6,1)); // alive moves | 4
        assertTrue(gameManager.isDay());
        assertFalse(gameManager.move(0,1,0,0)); // dead tries to moves | 5
        assertTrue(gameManager.move(4,5,3,5)); // dead moves | 6
        assertTrue(gameManager.isDay());
        assertTrue(gameManager.move(5,6,4,5)); // alive moves | 7
        assertTrue(gameManager.move(3,5,2,5)); // dead moves | 8
        assertFalse(gameManager.isDay());
        assertTrue(gameManager.move(6,1,4,3)); // alive moves | 9
        assertTrue(gameManager.move(2,5,1,5)); // dead moves | 10
        assertTrue(gameManager.move(4,5,5,4)); // alive moves | 11
        assertTrue(gameManager.move(1,5,2,5)); // dead moves | 12
        assertTrue(gameManager.move(4,3,6,1)); // alive moves | 9
        assertTrue(gameManager.isDay());
        assertTrue(gameManager.move(2,5,1,5)); // dead moves | 12
        assertTrue(gameManager.move(5,4,6,3)); // alive moves | 13
        assertTrue(gameManager.move(1,5,2,5)); // dead moves | 12
        assertTrue(gameManager.move(2,2,2,3)); // dead moves | 12
        assertTrue(gameManager.move(2,5,1,5)); // dead moves | 12
        assertTrue(gameManager.isDay());
        assertEquals("H:8", gameManager.getSquareInfo(6,3));
        assertTrue(gameManager.move(6,3,5,2)); // alive moves | 16
        assertEquals("E:-1", gameManager.getSquareInfo(6,3));
    }

    @Test public void testSaveAndLoad() throws IOException {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        File file = new File("test-files/testSave.txt");
        assertEquals("Z:1", gameManager.getSquareInfo(3,3));
        assertTrue(gameManager.move(3,3,2,3));
        assertEquals("", gameManager.getSquareInfo(3,3));
        assertEquals("H:7", gameManager.getSquareInfo(4,3));
        assertTrue(gameManager.move(4,3,2,1));
        assertEquals("", gameManager.getSquareInfo(4,3));
        assertEquals("H:7", gameManager.getSquareInfo(2,1));
        gameManager.saveGame(file);
        gameManager.loadGame(new File("test-files/testSave.txt"));
        assertEquals("", gameManager.getSquareInfo(4,3));
        assertEquals("H:7", gameManager.getSquareInfo(2,1));
        assertEquals("", gameManager.getSquareInfo(3,3));
    }

    @Test public void testGameIsOver() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        assertFalse(gameManager.gameIsOver());
        assertTrue(gameManager.move(1,1,1,2)); // dead moves | 1
        assertTrue(gameManager.move(3,4,2,4)); // alive moves | 2
        assertFalse(gameManager.move(1,2,2,2)); // dead tries move to dog
        assertTrue(gameManager.move(3,3,2,3)); // dead moves | 3
        assertTrue(gameManager.move(2,2,2,1)); // alive moves | 4
        assertTrue(gameManager.move(1,2,1,1)); // dead moves | 5
        assertTrue(gameManager.move(4,3,4,2)); // alive moves | 6
        assertFalse(gameManager.move(1,1,2,1)); // dead tries move
        assertTrue(gameManager.move(2,3,1,3)); // dead moves | 7
        assertFalse(gameManager.gameIsOver());
        assertTrue(gameManager.move(2,1,3,1)); // alive moves | 8
        assertTrue(gameManager.gameIsOver());
        assertEquals("E:-3", gameManager.getSquareInfo(2,1));
        assertEquals("H:10", gameManager.getSquareInfo(3,1));
    }

    @Test public void testGameIsOver2SH() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/7x7_5H_5Z_4E_2SH.txt"));
        assertTrue(gameManager.move(1, 1, 2, 1));
        assertTrue(gameManager.move(2, 2, 4, 2));
        assertTrue(gameManager.move(4, 5, 4, 4));
        assertTrue(gameManager.move(4, 2, 4, 0));
        assertTrue(gameManager.move(3, 3, 4, 3));
        assertTrue(gameManager.move(4, 0, 6, 0));
        assertTrue(gameManager.move(4, 4, 3, 4));
        assertTrue(gameManager.move(6, 5, 6, 3));
        assertTrue(gameManager.move(5, 3, 5, 4));
        assertTrue(gameManager.move(6, 3, 6, 1));
        assertTrue(gameManager.move(5, 4, 5, 5));
        assertTrue(gameManager.move(6, 1, 6, 0));
        assertFalse(gameManager.gameIsOver());
        assertTrue(gameManager.move(5, 5, 5, 6));
        assertTrue(gameManager.gameIsOver());
        List<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(9);
        assertEquals(ids, gameManager.getIdsInSafeHaven());
    }
}
