package services;

import models.Match;
import models.Score;

public class MatchScoreCalculationService {
    private Match match;
    private Score scoreCurrentPlayer;
    private Score scoreOtherPlayer;
    private String currentPlayerName;
    private final int ZERO_0 = 0;
    private final int FIFTEEN_15 = 15;
    private final int THIRTY_30 = 30;
    private final int FORTY_40 = 40;
    private final int QTY_SETS_TO_WIN = 2;
    private final int QTY_POINTS_TO_DEUCE = 40;
    private final int QTY_GAMES_TO_TIE_BREAK = 6;
    private final int QTY_TO_WIN_TIE_BREAK = 7;
    private final int DIFFERENCE_FOR_WIN_DRAW = 2;


    public MatchScoreCalculationService(Match match, String name) {
        this.match = match;
        this.currentPlayerName = name;
        this.scoreCurrentPlayer = this.match.getMatchScoreByKey(name);
        this.scoreOtherPlayer = this.match.getOtherPlayerByKey(name);
    }


    public Match getScoreCalc() {
        if (this.match.getOver()) {
            return null;
        }

        if (this.match.getTieBreak()) {
            int pointsTieBreak = this.scoreCurrentPlayer.getPointsTieBreak();
            this.scoreCurrentPlayer.setPointsTieBreak(++pointsTieBreak);
            if (this.isOverTieBreak(pointsTieBreak)) {
                this.match.setTieBreak(false);
            }
        } else if (this.match.getDeuce()) {
            int pointsDeuce = this.scoreCurrentPlayer.getPointsDeuce();
            this.scoreCurrentPlayer.setPointsDeuce(++pointsDeuce);
            if (this.isOverDeuce(pointsDeuce)) {
                this.match.setDeuce(false);
            }
        } else {
            this.calcQtyPoints();
        }
        if (this.scoreCurrentPlayer.getSets() == QTY_SETS_TO_WIN) {
            this.match.setOver(true);
            this.writeMsg("Match is over!");
            this.match.setWinner(this.match.getPlayerByName(this.currentPlayerName));
        }
        return this.match;
    }

    private void calcQtyGames() {
        int currentPlayerGames = this.scoreCurrentPlayer.getGames();
        this.scoreCurrentPlayer.setGames(++currentPlayerGames);
        this.scoreCurrentPlayer.setPoints(ZERO_0);
        this.scoreOtherPlayer.setPoints(ZERO_0);
        this.writeNumberGamesSetsMsg();
        if (currentPlayerGames >= QTY_GAMES_TO_TIE_BREAK) {
            if (this.scoreOtherPlayer.getGames() == QTY_GAMES_TO_TIE_BREAK && currentPlayerGames == QTY_GAMES_TO_TIE_BREAK) {
                this.match.setTieBreak(true);
                this.writeMsg("Play tie-break!");
            } else {
                this.checkWinnerSet();
            }
        }
    }

    private void calcQtyPoints() {
        int pointsCurrentPlayer = this.scoreCurrentPlayer.getPoints();
        switch (pointsCurrentPlayer) {
            case ZERO_0:
                this.scoreCurrentPlayer.setPoints(FIFTEEN_15);
                break;
            case FIFTEEN_15:
                this.scoreCurrentPlayer.setPoints(THIRTY_30);
                break;
            case THIRTY_30:
                this.scoreCurrentPlayer.setPoints(FORTY_40);
                if (this.scoreOtherPlayer.getPoints() == QTY_POINTS_TO_DEUCE) {
                    this.match.setDeuce(true);
                    this.writeMsg("Play 40:40 (deuce)!");
                }
                break;
            case FORTY_40:
                this.calcQtyGames();
                break;
        }
    }

    private boolean isOverTieBreak(int pointsCurrentPlayer) {
        int pointsOtherPlayer = this.scoreOtherPlayer.getPointsTieBreak();

        if (pointsCurrentPlayer >= QTY_TO_WIN_TIE_BREAK && pointsCurrentPlayer - pointsOtherPlayer >= DIFFERENCE_FOR_WIN_DRAW) {
            int currentPlayerSets = this.scoreCurrentPlayer.getSets();
            this.scoreCurrentPlayer.setSets(++currentPlayerSets);

            this.scoreCurrentPlayer.setPointsTieBreak(ZERO_0);
            this.scoreOtherPlayer.setPointsTieBreak(ZERO_0);

            this.scoreCurrentPlayer.setGames(ZERO_0);
            this.scoreOtherPlayer.setGames(ZERO_0);
            this.writeNumberGamesSetsMsg();

            return true;
        }
        return false;
    }

    private boolean isOverDeuce(int pointsCurrentPlayer) {
        int pointsOtherPlayer = this.scoreOtherPlayer.getPointsDeuce();
        if (pointsOtherPlayer > ZERO_0) {
            this.scoreOtherPlayer.setPointsDeuce(--pointsOtherPlayer);
        }
        if (pointsCurrentPlayer - pointsOtherPlayer >= DIFFERENCE_FOR_WIN_DRAW) {
            int currentPlayerGames = this.scoreCurrentPlayer.getGames();
            this.scoreCurrentPlayer.setGames(++currentPlayerGames);
            this.scoreCurrentPlayer.setPointsDeuce(ZERO_0);
            this.scoreOtherPlayer.setPointsDeuce(ZERO_0);

            this.scoreCurrentPlayer.setPoints(ZERO_0);
            this.scoreOtherPlayer.setPoints(ZERO_0);
            this.writeNumberGamesSetsMsg();

            return true;
        }
        return false;
    }

    private void writeMsg(String msg) {
        this.match.setMessage(msg);
    }

    private void writeNumberGamesSetsMsg() {
        int game = Math.max(this.scoreCurrentPlayer.getGames(), this.scoreOtherPlayer.getGames()) + 1;
        int set = this.scoreCurrentPlayer.getSets() + this.scoreOtherPlayer.getSets() + 1;
        this.match.setMessage("Play: " + set + " set, " + game + " game!");
    }

    private void writeWinnerSetMsg() {
        int set = this.scoreCurrentPlayer.getSets() + this.scoreOtherPlayer.getSets() ;
        this.match.setMessage("Player: " + this.currentPlayerName + " is the winner in " + set + " set!");
    }

    private void checkWinnerSet() {
        int gamesFirstPlayer = this.scoreCurrentPlayer.getGames();
        int gamesSecondPlayer = this.scoreOtherPlayer.getGames();

        if (gamesFirstPlayer - gamesSecondPlayer >= DIFFERENCE_FOR_WIN_DRAW) {
            int sets = this.scoreCurrentPlayer.getSets();
            this.scoreCurrentPlayer.setSets(++sets);
            this.scoreCurrentPlayer.setGames(ZERO_0);
            this.scoreOtherPlayer.setGames(ZERO_0);
            this.scoreOtherPlayer.setPoints(ZERO_0);
            this.writeWinnerSetMsg();
        }
    }
}


