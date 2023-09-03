package nextstep.blackjack;

public class Dealer extends Participant {

    public Dealer() {
        super("Dealer");
    }

   @Override
   public boolean doHit(boolean isHit) {
        boolean isDealerHit = false;
        if(state.cards().isUnderSeventeen())
            isDealerHit = true;

        boolean result = super.doHit(isDealerHit);
        state = state.stay();
        return result;
   }

    @Override
    public boolean isDealer() {
        return true;
    }


    @Override
    public boolean isPlayer() {
        return false;
    }
}
