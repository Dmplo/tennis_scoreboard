package services;

import Infrastructure.entityService.ServiceFactory;
import Infrastructure.entityService.iMatchService;
import models.Match;
import models.Player;

public class CreateMatchesService {

    public void addMatches() {
        try {
            iMatchService matchService = ServiceFactory.getService(iMatchService.class);
            assert matchService != null;
            for (int i = 0; i < Names.values().length; i+=2) {
                matchService.create(this.createMatch(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Match createMatch(int i) {
        Player firstPlayer = new Player(String.valueOf(Names.values()[i++]));
        Player secondPlayer = new Player(String.valueOf(Names.values()[i]));
        Match match = new Match(firstPlayer, secondPlayer);
        match.setWinner(firstPlayer);
        return match;
    }
}
