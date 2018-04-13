package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
			this.description = "Take 50% less damage for "+ amount + " attack.";
			if(this.amount > 1)
				this.description = "Take 50% less damage for "+ amount + " attacks.";
		}
	}

	@Override
	public float atDamageFinalReceive(float damage, DamageType type) {
		damage *= 0.5f;
		return damage;
	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		amount--;
		if(amount <= 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
		this.flash();
		return damageAmount;
	}
	
}
