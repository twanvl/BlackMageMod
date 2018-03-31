package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DrawCardPower extends AbstractPower{
	
	private int potency;
	
	public DrawCardPower(AbstractCreature owner, int potency, int amount) {
		this.name = "Vitality";
		this.ID = "bm_drawcard_power";
		this.owner = owner;
		this.amount = amount;
		this.potency = potency;
		this.isTurnBased = true;
		
		updateDescription();
		
		this.type = PowerType.BUFF;
		loadRegion("carddraw");
		
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = "Draw " + potency + " extra cards for "+ amount + " turn.";
			if(this.amount > 1)
				this.description = "Draw " + potency + " extra cards for "+ amount + " turn.";
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
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, potency));
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
