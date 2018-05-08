package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import blackmage.BlackMageMod;
import blackmage.cards.AbstractCustomCardWithType;
import blackmage.cards.AbstractCustomCardWithType.CardColorType;

public class FreezePower extends AbstractPower {
	public FreezePower(AbstractCreature owner, int power) {
		this.name = "Freeze";
		this.ID = "bm_freeze_power";
		this.owner = owner;
		this.amount = power;
		
		updateDescription();
		
		this.type = PowerType.BUFF;
		this.img = BlackMageMod.getTexture("img/powers/ice.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = "Whenever you play an #bIce type card gain #y" + amount + " block.";
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if(card instanceof AbstractCustomCardWithType) {
			AbstractCustomCardWithType aCard = (AbstractCustomCardWithType)card;
			if(aCard.colorType == CardColorType.ICE) {
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount));
			}
		}
	}

	public void stackPower(int stackAmount) {
		this.fontScale = 8.0f;
		this.amount += stackAmount;
		if(this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
		
		if (this.amount >= 999) {
			this.amount = 999;
		}
	}
	
	public void reducePower(int reduceAmount) {
		this.fontScale = 8.0f;
		this.amount -= reduceAmount;
		if(this.amount == 0) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
		
		if (this.amount >= 999) {
			this.amount = 999;
		}
	}
}
