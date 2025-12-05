package edu.io;
import edu.io.player.Player;
import edu.io.token.*;

import java.util.Scanner;

public class Game {

    private Player player;
    private Board board;

    public Game() {
        this.board = new Board();
    }

    public void join(Player player) {
        this.player = player;
        PlayerToken token = new PlayerToken(player,board);
        player.assignToken(token);
    }

    public void start(){

        GoldToken goldToken1 = new GoldToken(3.74);
        GoldToken goldToken2 = new GoldToken(4.14);
        PickaxeToken pickaxeToken1 = new PickaxeToken();

        board.placeToken(0,0,goldToken1);
        board.placeToken(2,6,goldToken2);
        board.placeToken(5,6,pickaxeToken1);

        join(new Player());
        
        board.display();

        System.out.println("Use: W to move UP");
        System.out.println("Use: S to move DOWN");
        System.out.println("Use: D to move RIGHT");
        System.out.println("Use: A to move LEFT");
        System.out.println("Use: Q to move QUIT");

        Scanner sc = new Scanner(System.in);

        //token() zwraca przypisany pionek na którym wywołuje move()
        try{
            while(true){

                String m = sc.nextLine().toUpperCase();

                if(m.equals("Q")){
                    break;
                }

                switch (m){
                    case "A":
                        player.token().move(PlayerToken.Move.LEFT);
                        break;
                    case "D":
                        player.token().move(PlayerToken.Move.RIGHT);
                        break;
                    case "W":
                        player.token().move(PlayerToken.Move.UP);
                        break;
                    case "S":
                        player.token().move(PlayerToken.Move.DOWN);
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
                board.display();
            }

        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }


    }
}
