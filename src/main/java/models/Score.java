package models;

public class Score {

    private int sets;
    private int games;
    private int points;
    private int pointsTieBreak;
    private int pointsDeuce;

    private Score(int sets, int games, int points, int pointsTieBreak, int pointsDeuce) {
        this.sets = sets;
        this.games = games;
        this.points = points;
        this.pointsTieBreak = pointsTieBreak;
        this.pointsDeuce = pointsDeuce;
    }

    public static Score init() {
        return new Score(0, 0, 0, 0, 0);
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPointsTieBreak() {
        return pointsTieBreak;
    }

    public void setPointsTieBreak(int pointsTieBreak) {
        this.pointsTieBreak = pointsTieBreak;
    }

    public int getPointsDeuce() {
        return pointsDeuce;
    }

    public void setPointsDeuce(int pointsDeuce) {
        this.pointsDeuce = pointsDeuce;
    }


    @Override
    public String toString() {
        return "ScoreInfoPlayer{" +
                "sets=" + sets +
                ", games=" + games +
                ", points=" + points +
                ", pointsTieBreak=" + pointsTieBreak +
                ", pointsDeuce=" + pointsDeuce +
                '}';
    }
}

