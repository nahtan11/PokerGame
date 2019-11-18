package output.Game;

//Mediator Design Pattern
public class GameTable {
    public static void showMove(Player player, String move){
        System.out.println(player.getName()+" made the move: "+move);
    }
}
