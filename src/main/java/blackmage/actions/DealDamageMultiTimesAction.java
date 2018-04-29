package blackmage.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DealDamageMultiTimesAction extends AbstractGameAction {

	private DamageInfo info;
	private AbstractCreature target;
	private int timesToDamage;
	
	public DealDamageMultiTimesAction(AbstractCreature target, DamageInfo info, int timesToDamage) {
		this.duration = Settings.ACTION_DUR_FAST;
		this.info = info;
		this.target = target;
		this.timesToDamage = timesToDamage;
	}
	
	@Override
	public void update() {
		if(this.duration == Settings.ACTION_DUR_FAST) {
			for(int i = 0; i < timesToDamage; i++) {
				AbstractDungeon.actionManager.addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
			}
		}
		tickDuration();
	}

}
