package edu.io.token;
import edu.io.Board;

public class PlayerToken extends Token {

    private Board board;
    private int col;
    private int row;

    public enum Move {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }

    public PlayerToken(Board board){
        this(board,0,0);
    }

    public PlayerToken(Board board,int col,int row) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;
        board.placeToken(col,row,this);
    }


    public void move(Move dir){

        int nrow = row;
        int ncol = col;

        EmptyToken emptyToken = new EmptyToken();
        switch (dir){
            case Move.UP:
                nrow--;
                break;
            case Move.DOWN:
                nrow++;
                break;
            case Move.LEFT:
                ncol--;
                break;
            case Move.RIGHT:
                ncol++;
                break;
            case Move.NONE:
                break;
            default:
                System.err.println("Invalid move direction");
                break;
        }
        if(ncol < 0 || nrow < 0 || ncol >= board.size() || nrow >= board.size()){
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        board.placeToken(col,row,emptyToken);
        col = ncol;
        row = nrow;
        board.placeToken(col,row,this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }


}