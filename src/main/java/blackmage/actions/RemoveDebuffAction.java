package blackmage.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;

public class RemoveDebuffAction extends AbstractGameAction {

	public RemoveDebuffAction(AbstractCreature target, AbstractCreature owner, int debuffsRemoved) {
		for(int i = 0; i < debuffsRemoved; i++) {
			ArrayList<AbstractPower> debuffs = new ArrayList<AbstractPower>();
			
			for(AbstractPower p : target.powers) {
				if(p.type == AbstractPower.PowerType.DEBUFF) {
					debuffs.add(p);
				}
			}
			
			if(!debuffs.isEmpty()) {
				AbstractPower randDebuff = debuffs.get((new Random()).random(debuffs.size() - 1));
				randDebuff.flash();
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(target, target, randDebuff.ID));
			}
		}
	}
	
	@Override
	public void update() {
		this.isDone = true;
	}

}
