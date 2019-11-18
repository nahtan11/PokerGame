package output.Game;

public class Player {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name){
        this.name  = name;
    }

    public void sendMove(String move){
        GameTable.showMove(this,move);
    }
}
