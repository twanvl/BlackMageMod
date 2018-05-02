package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;

public class IcePower extends AbstractPower {
	
	public static final String[] DESCRIPTIONS = new String[] {
		"#bIce type attacks deal #y"
	};
	
	private static int cap = 5;
	
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
		this.img = BlackMageMod.getTexture("img/powers/ice.png");
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
		if(this.amount >= cap) {
			this.flash();
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, 5));
			this.amount = 1;
			//cap++;
		}
	}
	
	public void reducePower(int reduceAmount) {
		this.amount -= reduceAmount;
	}
	
	@Override
	public void updateDescription() {
		if(this.amount > 0) {
			this.description = (
				"#yPassive #yEffect: NL " + 
				DESCRIPTIONS[0] + 
				this.amount + 
				" more damage this turn. NL NL #rActive #rEffect: NL At " + 
				cap + 
				" or more stacks gain #y5 block and reset stacks to #y1 and increase Ice cap by 1.");
		}
	}
}
