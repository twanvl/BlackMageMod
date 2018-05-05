package blackmage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;
import blackmage.cards.AbstractCustomCardWithType;
import blackmage.cards.AbstractCustomCardWithType.CardColorType;

public class ScorchPower extends AbstractPower {
	public ScorchPower(AbstractCreature owner, int power) {
		this.name = "Scorch";
		this.ID = "bm_scorch_power";
		this.owner = owner;
		this.amount = power;
		
		updateDescription();
		
		this.type = PowerType.BUFF;
		this.img = BlackMageMod.getTexture("img/powers/fire.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = "Whenever you play a #rFire type card deal #y" + amount + " damage to a random enemy.";
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if(card instanceof AbstractCustomCardWithType) {
			AbstractCustomCardWithType aCard = (AbstractCustomCardWithType)card;
			if(aCard.colorType == CardColorType.FIRE) {
				AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(owner, 1, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
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
