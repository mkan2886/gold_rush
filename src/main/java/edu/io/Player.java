package edu.io;
import edu.io.token.PlayerToken;
import edu.io.token.GoldToken;
import edu.io.token.Token;

public class Player {

    private PlayerToken token;
    private double gold;

    public void assignToken(PlayerToken player) {
        this.token = player;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double gold) {
        if(gold >= 0){
            this.gold += gold;
        }else throw new IllegalArgumentException("Gold amount cannot be negative");

    }

    public void loseGold(double gold) {
        if(gold < 0){
            throw new IllegalArgumentException("Gold amount cannot be negative");
        }
        if(gold > this.gold){
            throw new IllegalArgumentException("Cannot lose more gold then you have");
        }
        this.gold -= gold;

    }
    public void interactWithToken(Token token) {

        if(token instanceof GoldToken gold){
            this.gainGold(gold.amount());
            //System.out.print(this.Gold());
        }
    }

}
