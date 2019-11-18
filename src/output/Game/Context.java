package output.Game;

//Strategy Design Pattern
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public String doStrategy(String game, String move){
        return strategy.makeMove(game, move);
    }
}
