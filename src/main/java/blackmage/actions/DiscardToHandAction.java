package blackmage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardToHandAction extends AbstractGameAction {
	
	AbstractPlayer player;

	public DiscardToHandAction(AbstractPlayer p) {
		player = p;
		setValues(null, p, this.amount);
		this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
		this.duration = Settings.ACTION_DUR_FASTER;
	}
	
	@Override
	public void update() {
		if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
			this.isDone = true;
			return;
		}
		AbstractCard tmp;
		if (this.duration == Settings.ACTION_DUR_FASTER) {
			if (player.discardPile.isEmpty()) {
				this.isDone = true;
				return;
			}
			if (player.discardPile.size() == 1) {
				tmp = player.discardPile.getTopCard();
				player.discardPile.removeCard(tmp);
				player.hand.addToHand(tmp);
			}
			if (player.discardPile.group.size() > this.amount) {
				AbstractDungeon.gridSelectScreen.open(player.discardPile, 1, "Pick a card to add to your hand.", false, false, false, false);
				tickDuration();
				return;
			}
		}
		if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
			for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
				player.discardPile.removeCard(card);
				player.hand.addToHand(card);
			}
			AbstractDungeon.gridSelectScreen.selectedCards.clear();
			AbstractDungeon.player.hand.refreshHandLayout();
		}
		tickDuration();
	}

}
