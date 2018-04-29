package blackmage.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackmage.BlackMageMod;

public class SpellBook extends CustomRelic {

	public static final String ID = "SpellBook";
	public static final RelicTier TIER = RelicTier.STARTER;
	
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
		BlackMageMod.applyRandomIceFirePower(1);
		flash();
	}
	
	
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}

	
}
