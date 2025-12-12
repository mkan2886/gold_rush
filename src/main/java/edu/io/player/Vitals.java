package edu.io.player;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Vitals {

    private int hydration;
    private Runnable onDeathCallback;

    public Vitals(){
        this.hydration = 100;
        onDeathCallback = () -> {};
    }

    public Vitals(int hydration) {
        if (hydration < 0 || hydration > 100){
            throw new IllegalArgumentException();
        }
        this.hydration = hydration;
    }

    public int hydration(){
        return  hydration;
    }

    public void hydrate(int amount){
        if(amount < 0 || amount > 100){
            throw new IllegalArgumentException();
        }

        //Nawodnienie nie przekroczy 100
        this.hydration = Math.min(100,this.hydration+amount);
       // this.hydration += amount;
    }

    public void dehydrate(int amount){
        if(amount < 0 || amount > 100){
            throw new IllegalArgumentException();
        }

        this.hydration = Math.max(0,this.hydration-amount);
       // this.hydration -= amount;
        if(this.hydration == 0){
            onDeathCallback.run();
        }
    }

    public boolean isAlive(){
        return hydration > 0;
    }

    public void setOnDeathHandler(@NotNull Runnable callback) {
        onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
        //this.onDeathCallback = callback;
    }
}
