package blackmage.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackmage.powers.FirePower;
import blackmage.powers.IcePower;

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
		AbstractPlayer p = AbstractDungeon.player;
		AbstractPower power = null;
		Random rand = new Random();
		int r = rand.random(1);
		if(!p.hasPower("bm_unleash_power")) {
			flash();
			if(r == 0) {
				power = new IcePower(p, 1);
			}else {
				power = new FirePower(p, 1);
			}
		} else {
			if(r == 0 && p.hasPower("bm_ice_power")) {
				power = new IcePower(p, 1);
			}else if(r == 1 && p.hasPower("bm_fire_power")) {
				power = new FirePower(p, 1);
			}
			flash();
		}
		
		if(power != null)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, 1));
	}
	
	
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}

	
}
