package service;

import org.junit.jupiter.api.*;

import static data.TestData.*;

public class TestGame {

    @Nested
    @DisplayName("Игрок, выигравший 2 сета из 3х выигрывает игру")
    public class CheckWinMatch1 {
        @BeforeEach
        public void setup() {
            scoreFirstPlayer.setSets(1);
            scoreSecondPlayer.setSets(1);

            scoreFirstPlayer.setGames(11);
            scoreSecondPlayer.setGames(10);

            scoreFirstPlayer.setPoints(40);
            scoreSecondPlayer.setPoints(0);
        }

        @Test

        public void testStart() {

            // Ход игрока
            controller.getScoreCalc(firstPlayer.getName(), key);

            int qtySetsFirst = scoreFirstPlayer.getSets();
            int qtyGamesFirst = scoreFirstPlayer.getGames();
            int qtyPointsFirst = scoreFirstPlayer.getPoints();

            int qtySetsSecond = scoreSecondPlayer.getSets();
            int qtyGamesSecond = scoreSecondPlayer.getGames();
            int qtyPointsSecond = scoreSecondPlayer.getPoints();

            boolean isMatchOverAfter = match.getOver();

            String nameWinner = match.getWinner().getName();

            String nameFirst = firstPlayer.getName();

            Assertions.assertTrue(isMatchOverAfter);
            Assertions.assertEquals(2, qtySetsFirst);
            Assertions.assertEquals(nameWinner, nameFirst);

            Assertions.assertEquals(0, qtyGamesFirst);
            Assertions.assertEquals(0, qtyPointsFirst);

            Assertions.assertEquals(1, qtySetsSecond);
            Assertions.assertEquals(0, qtyGamesSecond);
            Assertions.assertEquals(0, qtyPointsSecond);

        }
    }
}