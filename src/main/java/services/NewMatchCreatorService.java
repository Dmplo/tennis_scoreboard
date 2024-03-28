package services;

import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iPlayerService;
import models.Match;
import models.Player;

public class NewMatchCreatorService {

    public Match createMatch(String firstPlayerName, String secondPlayerName) {

        iPlayerService playerService = ServiceFactory.getService(iPlayerService.class);
        assert playerService != null;

        Player firstPlayer;
        Player secondPlayer;

        try {
            firstPlayer = playerService.getPlayerByName(firstPlayerName);
        } catch (Exception e) {
            firstPlayer = new Player(firstPlayerName);
        }

        try {
            secondPlayer = playerService.getPlayerByName(secondPlayerName);
        } catch (Exception e) {
            secondPlayer = new Player(secondPlayerName);

        }
        return new Match(firstPlayer, secondPlayer);
    }
}
