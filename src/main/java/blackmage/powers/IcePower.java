package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomPlayer;
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
		this.isTurnBased = true;
		
		if (this.amount >= 999)
			this.amount = 999;
		
		updateDescription();
		
		if(owner.hasPower("bm_fire_power")) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "bm_fire_power"));
			BlackMageMod.setOrbColor(this, (CustomPlayer)owner, true);
		}
		
		BlackMageMod.setOrbColor(this, (CustomPlayer)owner, false);
		
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getIcePowerTexture();
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if(isPlayer) {
			if(!AbstractDungeon.player.hasPower("bm_unleash_power")) {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
				BlackMageMod.resetOrbColor((CustomPlayer)owner);
			}else {
				this.reducePower((int)Math.ceil((amount / 2.0f)));
			}
		}
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
}
