package blackmage.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;

public class FirePower extends AbstractPower {
	
	public static final String[] DESCRIPTIONS = new String[] {
			"#rFire type attacks deal #y"
		};
	
	public FirePower(AbstractCreature owner, int amount) {
		this.name = "Fire";
		this.ID = "bm_fire_power";
		this.owner = owner;
		this.amount = amount;
		this.isTurnBased = true;
		
		if (this.amount >= 999)
			this.amount = 999;
		
		updateDescription();
		
		if(owner.hasPower("bm_ice_power")) {
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "bm_ice_power"));
			BlackMageMod.setOrbColor(this, (CustomPlayer)owner, true);
		}
		
		BlackMageMod.setOrbColor(this, (CustomPlayer)owner, false);
		
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getTexture("img/powers/fire.png");
	}
	
	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if(isPlayer) {
			if(!AbstractDungeon.player.hasPower("bm_unleash_power")) {
				this.reducePower((int)Math.ceil((amount / 2.0f)));
				BlackMageMod.resetOrbColor((CustomPlayer)owner);
			}
			System.out.println(this.amount);
			if(this.amount <= 0) {
				AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
			}
		}
	}

	public void stackPower(int stackAmount) {
		this.amount += stackAmount;
		if(this.amount >= 5) {
			this.flash();
			AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
					new DamageInfo(owner, 5, DamageInfo.DamageType.THORNS), 
					AttackEffect.FIRE));
			this.amount = 1;
		}
	}
	
	public void reducePower(int reduceAmount) {
		this.amount -= reduceAmount;
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = ("#yPassive #yEffect: NL " + DESCRIPTIONS[0] + this.amount + " more damage this turn. NL NL #bActive #bEffect: NL At #y5 or more stacks deal #y5 damage to a random enemy and reset stacks to #y1.");
		}
	}
}
