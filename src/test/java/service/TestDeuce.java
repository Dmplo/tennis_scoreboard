package service;

import org.junit.jupiter.api.*;

import static data.TestData.*;

public class TestDeuce {

    @BeforeEach
    public void setup() {
        scoreFirstPlayer.setPoints(40);
        scoreSecondPlayer.setPoints(40);
        match.setDeuce(true);
    }

    @Test
    @DisplayName("При выигрыше очка при счёте 40-40, гейм не заканчивается")
    public void testDeuce() {

        int qtyGamesBefore = scoreFirstPlayer.getGames();
        int qtyPointsDeuceBefore = scoreFirstPlayer.getPointsDeuce();

        // Ход игрока
        controller.getScoreCalc(firstPlayer.getName(), key);

        int qtyGamesAfter = scoreFirstPlayer.getGames();
        int qtyPointsDeuceAfter = scoreFirstPlayer.getPointsDeuce();

        Assertions.assertEquals(qtyGamesBefore, qtyGamesAfter);
        Assertions.assertNotEquals(qtyPointsDeuceBefore, qtyPointsDeuceAfter);
    }

    @Test
    @DisplayName("При 40-40, игрок выигрывает 2 сбрасывания подряд, гейм заканчивается в его пользу")
    public void testDeuce2() {

        int qtyGamesBefore = scoreFirstPlayer.getGames();
        scoreFirstPlayer.setPointsDeuce(1);

        // Ход игрока
        controller.getScoreCalc(firstPlayer.getName(), key);

        int qtyGamesAfter = scoreFirstPlayer.getGames();
        int qtyPointsDeuceAfter = scoreFirstPlayer.getPointsDeuce();

        Assertions.assertEquals(1, qtyGamesAfter - qtyGamesBefore);
        Assertions.assertEquals(0, qtyPointsDeuceAfter);
    }


}