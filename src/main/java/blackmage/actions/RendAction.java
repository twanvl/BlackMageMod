package blackmage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RendAction extends AbstractGameAction {

	private AbstractPlayer p;
	private int amount = 1;
	
	public RendAction(boolean upgraded) {
		this.actionType = ActionType.CARD_MANIPULATION;
		p = AbstractDungeon.player;
		this.duration = Settings.ACTION_DUR_FAST;
		if(upgraded)
			amount = 2;
	}
	
	@Override
	public void update() {
		if(this.duration == Settings.ACTION_DUR_FAST) {
			if(p.hand.isEmpty()) {
				this.isDone = true;
				return;
			}
			
			if(p.hand.size() == 1) {
				if(p.hand.getBottomCard().type == CardType.CURSE) {
					AbstractDungeon.actionManager.addToTop(new GainEnergyAction(amount));
				}
				
				p.hand.moveToExhaustPile(p.hand.getBottomCard());
				tickDuration();
				return;
			}
			AbstractDungeon.handCardSelectScreen.open("Pick a card to Exhaust.", 1, false);
			tickDuration();
			return;
		}
		
		if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
			for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
				if(c.type == CardType.CURSE) {
					AbstractDungeon.actionManager.addToTop(new GainEnergyAction(amount));
				}
				p.hand.moveToExhaustPile(c);
			}
			AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
			AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
		}
	}

}
