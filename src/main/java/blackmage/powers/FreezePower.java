package blackmage.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;

public class FreezePower extends AbstractPower{
	
	public static final String[] DESCRIPTIONS = new String[] {
			"Target is frozen for 1 turn."
		};
	
	private byte moveByte;
	private AbstractMonster.Intent moveIntent;
	
	
	public FreezePower(AbstractCreature owner) {
		this.name = "Freeze";
		this.ID = "bm_Freeze";
		this.owner = owner;
		this.amount = -1;
		this.updateDescription();
	 
		this.type = AbstractPower.PowerType.DEBUFF;
		this.img = BlackMageMod.getIcePowerTexture();
		
		moveByte = 1;
		moveIntent = AbstractMonster.Intent.UNKNOWN;
		
		if (owner instanceof AbstractMonster) {
			AbstractMonster m = (AbstractMonster)owner;
			
			moveByte = Byte.valueOf(m.nextMove);
			moveIntent = AbstractMonster.Intent.valueOf(m.intent.name());
			
			m.setMove(Byte.MAX_VALUE, AbstractMonster.Intent.STUN);
			m.createIntent();
			AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, Byte.MAX_VALUE, AbstractMonster.Intent.STUN));
		}
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}

	@Override
	public void atEndOfRound() {
		AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		
		if(owner instanceof AbstractMonster) {
			AbstractMonster m = (AbstractMonster)owner;
			
			m.setMove(moveByte, moveIntent);
			m.createIntent();
			AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, moveByte, moveIntent));
		}
	}
	

}
