package blackmage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class IcePower extends AbstractPower {
	
	public static final String[] DESCRIPTIONS = new String[] {
		"Ice type attacks deal "
	};
	
	public IcePower(AbstractCreature owner, int amount) {
		this.name = "Ice";
		this.ID = "bm_ice_power";
		this.owner = owner;
		this.amount = amount;
		
		if (this.amount >= 999)
			this.amount = 999;
		
		updateDescription();
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getIcePowerTexture();
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		System.out.println("End of turn: " + isPlayer);
	}

	public void stackPower(int stackAmount) {
		this.fontScale = 8.0f;
		this.amount += stackAmount;
		if(this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.name));
		}
		
		if (this.amount >= 999) {
			this.amount = 999;
		}
	}
	
	public void reducePower(int reduceAmount) {
		this.fontScale = 8.0f;
		this.amount -= reduceAmount;
		if(this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.name));
		}
		
		if (this.amount >= 999) {
			this.amount = 999;
		}
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = (DESCRIPTIONS[0] + this.amount + " more damage this turn.");
		}
	}

	@Override
	public float atDamageGive(float damage, DamageType type) {
		if(type == EnumPatch.ICE_DAMAGE) {
			return damage += this.amount;
		}
		return damage;
	}
	
	public void atEndOfRound() {
		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
	
	

	@Override
	public void onAfterCardPlayed(AbstractCard usedCard) {
		System.out.println(usedCard.cardID);
		if(usedCard.damageTypeForTurn == EnumPatch.FIRE_DAMAGE) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
	}
}