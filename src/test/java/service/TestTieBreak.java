package service;

import org.junit.jupiter.api.*;

import static data.TestData.*;

public class TestTieBreak {

    @Nested
    @DisplayName("Когда 6 - 6 начинается Тай бреак")
    public class CheckStartTieBreak {
        @BeforeEach
        public void setup() {
            scoreFirstPlayer.setGames(5);
            scoreFirstPlayer.setPoints(40);
            scoreSecondPlayer.setGames(6);
        }

        @Test
        public void testStart() {

            int qtySetsBefore = scoreFirstPlayer.getSets();

            // Ход игрока
            controller.getScoreCalc(firstPlayer.getName(), key);

            int qtySetsAfter = scoreFirstPlayer.getSets();
            boolean isTieBreak = match.getTieBreak();

            Assertions.assertEquals(qtySetsBefore, qtySetsAfter);
            Assertions.assertTrue(isTieBreak);
        }


    }

    @Nested
    @DisplayName("При разнице счета в 2 очка Тай бреак заканчивается, игрок выигрывает сет")
    public class CheckEndTieBreak {
        @BeforeEach
        public void setup() {
            scoreFirstPlayer.setPointsTieBreak(13);
            scoreSecondPlayer.setPointsTieBreak(12);
            match.setTieBreak(true);
        }

        @Test
        public void testStart() {

            int qtySetsBefore = scoreFirstPlayer.getSets();

            // Ход игрока
            controller.getScoreCalc(firstPlayer.getName(), key);

            int qtySetsAfter = scoreFirstPlayer.getSets();
            boolean isTieBreak = match.getTieBreak();
           int pointsTieBreakFirstPlayer = scoreFirstPlayer.getPointsTieBreak();
           int pointsTieBreakSecondPlayer = scoreSecondPlayer.getPointsTieBreak();

            Assertions.assertEquals(1, qtySetsAfter - qtySetsBefore);
            Assertions.assertFalse(isTieBreak);
            Assertions.assertEquals(0, pointsTieBreakFirstPlayer);
            Assertions.assertEquals(0, pointsTieBreakSecondPlayer);
        }
    }
}