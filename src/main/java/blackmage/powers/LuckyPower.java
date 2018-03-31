package blackmage.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;

import blackmage.BlackMageMod;

public class LuckyPower extends AbstractPower{
	
	private boolean nextIsCrit = false;
	
	public static final String[] DESCRIPTIONS = new String[] {
		"After you use an attack, the next attack has a 25% chance of dealing 3x damage."
	};
	
	public LuckyPower(AbstractCreature owner) {
		this.name = "Lucky";
		this.ID = "bm_lucky_power";
		this.owner = owner;
		this.amount = -1;
		
		
		updateDescription();
		
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getLuckyPowerTexture();
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		Random rand = new Random();
		if(usedCard.type == AbstractCard.CardType.ATTACK) {
			nextIsCrit = rand.randomBoolean(0.25f);
			this.flash();
		}
	}

	@Override
	public float atDamageGive(float damage, DamageType type) {
		if(nextIsCrit) {
			return damage * 3;
		}
		return damage;
	}	
}
