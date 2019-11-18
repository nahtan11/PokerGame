package output.Game;

public class Check implements IStrategy {
    @Override
    public String makeMove(String game, String move) {
        return game +" "+ move;
    }
}
