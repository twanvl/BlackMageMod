package blackmage.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import blackmage.BlackMageMod;

public class UnleashPower extends AbstractPower {
	
	public static final String[] DESCRIPTIONS = new String[] {
			"Only Lose half of your Ice or Fire at the end of your turn."
		};
	
	public UnleashPower(AbstractCreature owner) {
		this.name = "Unleash";
		this.ID = "bm_unleash_power";
		this.owner = owner;
		this.amount = -1;
		
		updateDescription();
		
		this.type = AbstractPower.PowerType.BUFF;
		this.img = BlackMageMod.getTexture("img/powers/unleash.png");
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
}
