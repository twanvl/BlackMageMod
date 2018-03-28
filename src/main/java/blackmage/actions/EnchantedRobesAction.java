package blackmage.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Shiv;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import blackmage.cards.Ember;
import blackmage.cards.Snowflake;

public class EnchantedRobesAction extends AbstractGameAction {
	
	ArrayList<AbstractCard> rewards = new ArrayList<AbstractCard>();
	private boolean retrieveCard = false;
	public static int numPlaced;
	
	public EnchantedRobesAction(){
		
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FAST;
		
		rewards.add(new Ember());
		rewards.add(new Snowflake());
		rewards.add(new Shiv());
	}

	@Override
	public void update() {
		if(this.duration == Settings.ACTION_DUR_FAST) {
			
			
			
			this.tickDuration();
			return;
		}
		if(!this.retrieveCard) {
			if(AbstractDungeon.cardRewardScreen.codexCard != null) {
				System.out.println(AbstractDungeon.cardRewardScreen.codexCard.cardID);
				
				AbstractDungeon.cardRewardScreen.codexCard = null;
			}
			this.retrieveCard = true;
		}
		this.tickDuration();
	}

}
