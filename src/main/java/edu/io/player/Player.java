package edu.io.player;
import edu.io.token.*;

public class Player {

    private PlayerToken token;
    public final Gold gold = new Gold();
    //private Token pickaxeToken = new EmptyToken();
    private Tool pickaxeToken = new NoTool();
    private Shed shed = new Shed();

    public void assignToken(PlayerToken player) {
        this.token = player;
    }

    public PlayerToken token() {
        return token;
    }


    public void interactWithToken(Token token) {

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
                System.out.println(gold.amount());
            }
            case PickaxeToken pfToken -> {
                this.pickaxeToken = pfToken;
                shed.add(pfToken);
            }

            case AnvilToken anvilToken -> {
                if (shed.getTool() instanceof Repairable tool) {
                    tool.repair();
                }
            }
            default -> {}}

        }
    }