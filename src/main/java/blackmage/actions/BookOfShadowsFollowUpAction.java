package blackmage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
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
						AbstractDungeon.actionManager.addToTop(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 5, DamageType.HP_LOSS), AttackEffect.BLUNT_LIGHT));
					}
				}
			}
			BookOfShadowsAction.checkedCards.clear();
		}
		tickDuration();
	}

}
