package output.Game;

public class Raise implements IStrategy{
    @Override
    public String makeMove(String game, String move) {
        return game +" "+ move;
    }
}
