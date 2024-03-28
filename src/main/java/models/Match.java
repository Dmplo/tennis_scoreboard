package models;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (cascade=CascadeType.MERGE)
    @JoinColumn (name="first_player_id")
    private Player firstPlayer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "second_player_id")
    private Player secondPlayer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "winner_id")
    private Player winner;

    @Transient
    private Map<String, Score> matchScore;

    private Boolean isTieBreak = false;
    private Boolean isOver = false;
    private Boolean isDeuce = false;
    private String message = "Play: 1 set, 1 game!";

    public Match() {

    }


    public Match(Player firstPlayer, Player secondPlayer) {
        this.matchScore = new HashMap<>();
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.fillScore(firstPlayer.getName(), secondPlayer.getName());
    }

    private void fillScore(String firstPlayerName, String secondPlayerName) {
        this.matchScore.put(firstPlayerName, Score.init());
        this.matchScore.put(secondPlayerName, Score.init());
    }

    public int getId() {
        return id;
    }

    public Map<String, Score> getMatchScore() {
        return matchScore;
    }

    public Score getMatchScoreByKey(String key) {
        return matchScore.get(key);
    }

    public Score getOtherPlayerByKey(String value) {
        String key = (this.firstPlayer.getName().equals(value) ? this.secondPlayer : this.firstPlayer).getName();
        return matchScore.get(key);
    }

    public Player getPlayerByName(String name) {
        return this.firstPlayer.getName().equals(name) ? this.firstPlayer : this.secondPlayer;
    }

    public Boolean getTieBreak() {
        return isTieBreak;
    }

    public void setTieBreak(Boolean tieBreak) {
        isTieBreak = tieBreak;
    }

    public Boolean getOver() {
        return isOver;
    }

    public void setOver(Boolean over) {
        isOver = over;
    }

    public Boolean getDeuce() {
        return isDeuce;
    }

    public void setDeuce(Boolean deuce) {
        isDeuce = deuce;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Match{" +
                ", firstPlayer=" + firstPlayer +
                ", secondPlayer=" + secondPlayer +
                ", winner=" + winner +
                ", matchScore=" + matchScore +
                ", isTieBreak=" + isTieBreak +
                ", isOver=" + isOver +
                ", isDeuce=" + isDeuce +
                ", message='" + message + '\'' +
                '}';
    }
}
