package edu.io.player;

public class Gold {

    private double amount;
    public Gold(){
        this(0.0);
    }

    public Gold(double amount) {

        if (amount < 0.0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount;
    }

    public double gold() {
        return amount;
    }

    public void gain(double gold) {
        if(gold >= 0){
            this.amount += gold;
        }else throw new IllegalArgumentException("Gold amount cannot be negative");
    }

    public double amount() {
        return amount;
    }

    public void lose(double gold) {
        if(gold < 0){
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }
        if(gold > this.amount){
            throw new IllegalArgumentException("Cannot lose more gold then you have");
        }
        this.amount -= gold;

    }
}
