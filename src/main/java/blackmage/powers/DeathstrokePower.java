package blackmage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import blackmage.BlackMageMod;

public class DeathstrokePower extends AbstractPower{
	
	private static final String DESCRIPTION = "Deal X Damage per card played.";
	private int potency;

	public DeathstrokePower(AbstractCreature owner, int potency) {
		this.name = "Deathstroke";
		this.ID = "bm_deathstroke_power";
		this.owner = owner;
		this.potency = potency;
		this.amount = -1;
		
		DESCRIPTION.replace("X", "" + potency);
		
		updateDescription();
		
		this.type = PowerType.DEBUFF;
		this.img = BlackMageMod.getTexture("img/powers/skull.png");
	}
	
	public void onAfterCardPlayed(AbstractCard usedCard) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(owner, new DamageInfo(owner, potency, DamageInfo.DamageType.HP_LOSS), AttackEffect.BLUNT_LIGHT));
	}
	
	
	
	public void updateDescription() {
		this.description = DESCRIPTION;
	}
}
