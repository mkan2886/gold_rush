package edu.io.player;
import edu.io.token.*;

public class Player {

    private PlayerToken token;
    public final Gold gold = new Gold();
    //public Vitals vitals = new Vitals();
    public Vitals vitals;
    private Tool pickaxeToken = new NoTool();
    private Shed shed = new Shed();

    public Player(){
        this.vitals = new Vitals();
        this.vitals.setOnDeathHandler(()->{
            System.out.println("You're completely dehydrated!");
        });
    }
    public void assignToken(PlayerToken player) {
        if(player == null){
            throw new NullPointerException("player is null");
        }
        this.token = player;

    }

    public PlayerToken token() {
        return token;
    }


    public void interactWithToken(Token token) {

        if(!vitals.isAlive()){
            throw new IllegalStateException("Player's dead");
        }


        switch (token) {
            case GoldToken goldToken -> {
                Tool tool = shed.getTool();
                double goldAmount = goldToken.amount();

                if (tool instanceof PickaxeToken pf) {
                    pf.useWith(goldToken)
                            .ifWorking(() -> {
                                gold.gain(goldAmount * pf.gainFactor());
                            })
                            .ifBroken(() -> {
                                gold.gain(goldAmount);
                                pickaxeToken = new NoTool();
                            })
                            .ifIdle(() -> {
                                gold.gain(goldAmount);
                            });
                } else {
                    gold.gain(goldAmount);
                }
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                System.out.println("gold: " + gold.amount());
                System.out.println("hydration: " + vitals.hydration());
            }
            case PickaxeToken pfToken -> {
                System.out.println("Pickaxe was collected");
                this.pickaxeToken = pfToken;
                shed.add(pfToken);
            }

            case AnvilToken anvilToken -> {
                if (shed.getTool() instanceof Repairable tool) {
                    tool.repair();
                }
                vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                System.out.println("hydration: " + vitals.hydration());
            }
            case WaterToken waterToken -> {
                vitals.hydrate(waterToken.amount());
                System.out.println("hydration: " + vitals.hydration());
            }
            default -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
                System.out.println("hydration: " + vitals.hydration());}
            }

        }
    }