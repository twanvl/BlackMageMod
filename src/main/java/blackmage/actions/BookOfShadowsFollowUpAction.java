package blackmage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import blackmage.cards.AbstractCustomCardWithType;
import blackmage.cards.AbstractCustomCardWithType.CardColorType;

public class BookOfShadowsFollowUpAction extends AbstractGameAction {

	public BookOfShadowsFollowUpAction() {
		this.duration = Settings.ACTION_DUR_FASTER;
	}
	@Override
	public void update() {
		if(this.duration == Settings.ACTION_DUR_FASTER) {
			for(AbstractCard c : BookOfShadowsAction.checkedCards) {
				if(c instanceof AbstractCustomCardWithType) {
					AbstractCustomCardWithType card = (AbstractCustomCardWithType)c;
					if(card.colorType == CardColorType.DARK) {
						AbstractCard customNormality = new Doubt();
						
						customNormality.isEthereal = true;
						customNormality.name = "Shackle";
						customNormality.rawDescription += " NL Ethereal.";
						customNormality.initializeDescription();
						
						AbstractDungeon.actionManager.addToTop(new MakeTempCardInDiscardAction(customNormality, 1));
					}
				}
			}
			BookOfShadowsAction.checkedCards.clear();
		}
		tickDuration();
	}

}
