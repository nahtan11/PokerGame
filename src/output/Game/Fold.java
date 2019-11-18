package output.Game;

public class Fold implements IStrategy {
    @Override
    public String makeMove(String game, String move) {
        return game +" "+ move;
    }
}
