package edu.io;
import edu.io.token.*;

public class Board {

    private final int size = 8;
    Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public record Coords(int col,int row){ }

    public void clean(){

       Token t = new EmptyToken();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j] = t;
            }
        }
    }

    public void placeToken(int col,int row, Token token){

        if(token == null){
            throw new NullPointerException();
        }
       if(col >= 0 && row >= 0 && col < size && row < size){
           grid[row][col] = token;
       }else{
           throw new IndexOutOfBoundsException("Invalid coordinates");
       }

    }

    public Coords getAvailableSquare(){

        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++) {
                if (grid[row][col] instanceof EmptyToken) {
                    return new Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Board is full");
    }


    public Token peekToken(int col,int row){

        if(col >= 0  && col < size && row >= 0 &&  row< size){
            return grid[row][col];
        }else {
            throw new IndexOutOfBoundsException("Invalid syntax");
        }
    }

    public void display(){

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++) {
                System.out.print(grid[i][j].toString()+" ");
            }
            System.out.println();
        }

    }

    public int size(){
        return size;
    }

}
