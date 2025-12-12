package edu.io.token;

public class WaterToken extends Token {

    private int amount;

    public WaterToken() {
        this(10);
    }
    public WaterToken(int amount) {
        super(Label.WATER_TOKEN_LABEL);
        if(amount<0 || amount>100){
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }
}
