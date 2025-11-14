package edu.io;
import edu.io.token.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Board board1 = new Board();
        PlayerToken token = new PlayerToken(board1);
        GoldToken goldToken = new GoldToken();
        board1.placeToken(3,4,goldToken);
        board1.placeToken(2,6,goldToken);

        board1.display();

        System.out.println("Use: W to move UP");
        System.out.println("Use: S to move DOWN");
        System.out.println("Use: D to move RIGHT");
        System.out.println("Use: A to move LEFT");
        System.out.println("Use: Q to move QUIT");

        Scanner sc = new Scanner(System.in);

        try{
            while(true){

                String m = sc.nextLine().toUpperCase();

                if(m.equals("Q")){
                    break;
                }

                switch (m){
                    case "A":
                        token.move(PlayerToken.Move.LEFT);
                        break;
                    case "D":
                        token.move(PlayerToken.Move.RIGHT);
                        break;
                    case "W":
                        token.move(PlayerToken.Move.UP);
                        break;
                    case "S":
                        token.move(PlayerToken.Move.DOWN);
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }

                board1.display();
            }

        }catch(IllegalArgumentException e){
            System.err.println(e.getMessage());
        }



    }
}
