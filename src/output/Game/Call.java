package output.Game;

public class Call implements IStrategy {
    @Override
    public String makeMove(String game, String move) {
        return game +" "+ move;
    }
}
