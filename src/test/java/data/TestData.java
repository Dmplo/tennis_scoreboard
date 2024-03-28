package data;


import controllers.MatchScoreController;
import models.Match;
import models.Player;
import models.Score;
import services.OngoingMatchesService;

public class TestData {
    public static final Player firstPlayer;
    public static final Player secondPlayer;
    public static final Match match;
    public static final String key;
    public static final MatchScoreController controller;
    public static Score scoreFirstPlayer;
    public static Score scoreSecondPlayer;

    static {
        firstPlayer = new Player("Alex");
        secondPlayer = new Player("Will");

        firstPlayer.setId(1);
        secondPlayer.setId(2);

        key = OngoingMatchesService.createMatchAndUUID(new Match(firstPlayer, secondPlayer));
        match = OngoingMatchesService.getMatchByKey(key);
        controller = new MatchScoreController();

        scoreFirstPlayer = match.getMatchScoreByKey(firstPlayer.getName());
        scoreSecondPlayer = match.getMatchScoreByKey(secondPlayer.getName());
    }
}
