package edu.io;

public class Board {

    public int size = 8;
    Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public void clean(){

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                grid[i][j] = new Token("・");
            }
        }
    }

    public void placeToken(int col,int row, Token token){
        grid[row][col] = token;
    }

    public Token square(int col,int row){

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



}
