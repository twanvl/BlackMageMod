package blackmage.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackmage.powers.FirePower;
import blackmage.powers.IcePower;

public class SpellBook extends CustomRelic {

	public static final String ID = "SpellBook";
	public static final RelicTier TIER = RelicTier.STARTER;
	
	/*private static final String[] DESCRIPTIONS = new String[] {
		"At the start of your turn gain a random elemental focus. Fire. Ice"
	};*/
	private static final LandingSound SOUND = LandingSound.MAGICAL;
	private static final String texturePath = "img/relics/spellbook.png";
	
	public SpellBook() {
		super(ID, getRelicTexture(), TIER, SOUND);
	}
	
	private static Texture getRelicTexture() {
		return new Texture(texturePath);
	}

	@Override
	public AbstractRelic makeCopy() {
		return new SpellBook();
	}

	@Override
	public void atTurnStart() {
		AbstractPlayer p = AbstractDungeon.player;
		Random rand = new Random();
		int r = rand.random(1);
		flash();
		if(r == 0) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 1), 1));
		}else {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}

	
}
