package edu.io;

import edu.io.token.Token;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        Token token = new Token("웃");
        board.placeToken(4,3,token);
        System.out.println( board.square(4,3));
        board.display();

    }
}
