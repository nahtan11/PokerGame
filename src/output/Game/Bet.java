package output.Game;

public class Bet implements IStrategy {
    @Override
    public String makeMove(String game, String move) {
        return game +" "+ move;
    }
}
