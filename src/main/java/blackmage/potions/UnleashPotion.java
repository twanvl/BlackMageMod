package blackmage.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import basemod.abstracts.CustomPotion;
import blackmage.powers.UnleashPower;

public class UnleashPotion extends CustomPotion {
	
	public static final String ID = "UnleashPotion";
	
	public UnleashPotion() {
		super("Unleash Potion", ID, AbstractPotion.PotionRarity.UNCOMMON, AbstractPotion.PotionSize.BOLT, AbstractPotion.PotionColor.POWER);
	}

	@Override
	public int getPotency(int arg0) {
		return 1;
	}

	@Override
	public AbstractPotion makeCopy() {
		return new UnleashPotion();
	}

	@Override
	public void use(AbstractCreature arg0) {
		AbstractPlayer player = AbstractDungeon.player;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player, new UnleashPower(player), 1));
	}

}
