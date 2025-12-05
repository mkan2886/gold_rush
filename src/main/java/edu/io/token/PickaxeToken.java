package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {

    private final double gainFactor;
    //private AnvilToken anvilToken;
    private int durability;
    private int inDurability;
    private Token withToken;

    public PickaxeToken(){
        this(1.5,3);
    }
    public PickaxeToken(double gainFactor) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if(gainFactor<=0){
            throw new IllegalArgumentException("gainFactor must be positive");
        }
        this.gainFactor = gainFactor;
        this.durability = 3;
        this.inDurability = 3;
    }

    public PickaxeToken(double gainFactor, int durability) {

        super(Label.PICKAXE_TOKEN_LABEL);
        this.gainFactor = gainFactor;

        if(durability<=0){
            throw new IllegalArgumentException("durability must be positive");
        }

        if(gainFactor<=0){
            throw new IllegalArgumentException("gainFactor must be positive");
        }

        this.durability = durability;
        this.inDurability = durability;
    }

    public double gainFactor(){
        return gainFactor;
    }
    public int durability(){
        return durability;
    }
    public void use(){
        if(durability>0){
            this.durability--;
        }
    }

    @Override
    public void repair(){
        this.durability=inDurability;
    }

    @Override
    public boolean isBroken(){
        return durability<=0;
    }


    @Override
    public PickaxeToken useWith(Token token){
       this.withToken = token;
       return this;
    }

    @Override
    public PickaxeToken ifBroken(Runnable action){
        if(isBroken()) action.run();
        return this;
    }

    @Override
    public PickaxeToken ifWorking(Runnable action){
        if(!isBroken() && withToken instanceof GoldToken) {
            use();
            action.run();
        }
        return this;
    }

    @Override
    public PickaxeToken ifIdle(Runnable action){
        if(!(withToken instanceof GoldToken)) action.run();
        return this;
    }

}

