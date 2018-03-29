package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;

public class Invigorate extends AbstractPower{
	
	public Invigorate(AbstractCreature owner, int amount) {
		this.name = "Invigorate";
		this.ID = "bm_invigorate_power";
		this.owner = owner;
		this.amount = amount;
		this.isTurnBased = true;
		
		updateDescription();
		
		this.type = PowerType.BUFF;
		this.img = BlackMageMod.getInvigoratePowerTexture();
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = "Gain 1 extra energy for "+ amount + " turn.";
			if(this.amount > 1)
				this.description = "Gain 1 extra energy for "+ amount + " turns.";
		}
	}
	
	public void atEndOfTurn(boolean isPlayer) {
		if(isPlayer) {
			this.amount -= 1;
			if(this.amount <= 0) {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
			}
		}
	}
	
	
	
	@Override
	public void atStartOfTurnPostDraw() {
		this.flash();
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
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
}
