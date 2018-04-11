package blackmage.powers;

import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;

public class DarkArmorPower extends AbstractPower{
	public DarkArmorPower(AbstractCreature owner, int amount) {
		this.name = "Dark Armor";
		this.ID = "bm_darkarmor_power";
		this.owner = owner;
		this.amount = amount;
		this.isTurnBased = true;
		
		updateDescription();
		
		this.type = PowerType.BUFF;
		this.img = BlackMageMod.getTexture("img/powers/darkarmor.png");
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = "Take 50% less damage for "+ amount + " turn.";
			if(this.amount > 1)
				this.description = "Take 50% less damage for "+ amount + " turns.";
		}
	}

	@Override
	public float atDamageFinalReceive(float damage, DamageType type) {
		
		damage *= 0.5f;
		
		return damage;
	}
}
