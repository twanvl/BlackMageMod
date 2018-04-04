package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;
import blackmage.cards.Ash;

public class GainAshNextTurnPower extends AbstractPower {
	
	public Ash ashCard = new Ash();
	
	public GainAshNextTurnPower(AbstractCreature owner, int amount) {
		this.name = "Ashen";
		this.ID = "bm_ash_power";
		this.owner = owner;
		this.amount = amount;
		
		updateDescription();
		
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getCardDrawPowerTexture();
		this.isTurnBased = true;
	}
	
	public void updateDescription() {
		this.description = "Gain ? Ash next turn.";
	}

	@Override
	public void atStartOfTurn() {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(ashCard.makeCopy(), amount));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
	}
}
