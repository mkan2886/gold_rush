package edu.io.token;
import edu.io.Board;
import edu.io.Player;

public class PlayerToken extends Token {

    private Board board;
    private Player player;
    private int col;
    private int row;

    public enum Move {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }

    public PlayerToken(Player player, Board board) {
        this(board, board.getAvailableSquare().col(), board.getAvailableSquare().row(),player);
    }

    public PlayerToken(Board board,int col,int row, Player player) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;
        this.player = player;
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

        player.interactWithToken(board.peekToken(ncol,nrow));
        board.placeToken(col,row,emptyToken);
        col = ncol;
        row = nrow;
        board.placeToken(col,row,this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }


}