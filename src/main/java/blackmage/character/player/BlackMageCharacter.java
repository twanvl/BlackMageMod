package blackmage.character.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;
import blackmage.particles.ParticleEffect;
import blackmage.patches.EnumPatch;

public class BlackMageCharacter extends CustomPlayer {
	
	public static final int ENERGY_PER_TURN = 3;
	public static final int START_HP = 75;
	public static final String[] orbTextures = {
			"img/character/orb/enabled/layer1.png",
			"img/character/orb/enabled/layer2.png",
			"img/character/orb/enabled/layer3.png",
			"img/character/orb/enabled/layer4.png",
			"img/character/orb/enabled/layer5.png",
			"img/character/orb/enabled/layer6.png",
			"img/character/orb/disabled/layer1d.png",
			"img/character/orb/disabled/layer2d.png",
			"img/character/orb/disabled/layer3d.png",
			"img/character/orb/disabled/layer4d.png",
			"img/character/orb/disabled/layer5d.png",
	};
	
	public static final String orbVfx = "img/character/orb/vfx.png";
	
	private ParticleEffect effectFront = new ParticleEffect(
			new Rectangle(370, 310, 200, 5),
			0.8f, 
			4, 
			40, 
			new Vector2(-0.002f, 0.135f), 
			BlackMageMod.MULTI);
	private ParticleEffect effectHand = new ParticleEffect(
			new Rectangle(425, 430, 5, 5),
			0.75f,
			4,
			50,
			new Vector2(0.0f, 0.05f),
			BlackMageMod.MULTI
			);

	public BlackMageCharacter(String name, PlayerClass setClass) {
		
		
		
		super(name, setClass, orbTextures, orbVfx, (String)null, (String)null);
		
		System.out.println("blackmage ctor : start");
		
		initializeClass(null, BlackMageMod.BLACK_MAGE_SHOULDER_2, 
				BlackMageMod.BLACK_MAGE_SHOULDER_1, BlackMageMod.BLACK_MAGE_CORPSE, 
				getLoadout(), 0.0f, -20f, 240.0f, 240.0f, new EnergyManager(ENERGY_PER_TURN));
		
		
		this.dialogX = (this.drawX + 0.0F * Settings.scale);
		this.dialogY = (this.drawY + 170.0F * Settings.scale);
		
		this.atlas = new TextureAtlas();
		
		initializeClass(null, BlackMageMod.BLACK_MAGE_SHOULDER_2, 
				BlackMageMod.BLACK_MAGE_SHOULDER_1, BlackMageMod.BLACK_MAGE_CORPSE, 
				getLoadout(), 0.0f, -20f, 240.0f, 240.0f, new EnergyManager(ENERGY_PER_TURN));
		
		System.out.println("blackmage ctor : finish");
	}
	
	@SuppressWarnings("unused")
	public void renderPlayerImage(SpriteBatch sb) {
		if(!(AbstractDungeon.player instanceof BlackMageCharacter))
			return;
		
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			effectFront.setParticleColor(BlackMageMod.BLUE);
			effectHand.setParticleColor(BlackMageMod.BLUE);
		}else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			effectFront.setParticleColor(BlackMageMod.RED);
			effectHand.setParticleColor(BlackMageMod.RED);
		}else {
			effectFront.setParticleColor(BlackMageMod.MULTI);
			effectHand.setParticleColor(BlackMageMod.MULTI);
		}
		
		
		sb.setColor(1, 1, 1, 1);
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		
		int x = Math.round(410 * Settings.scale);
		int y = Math.round(320 * Settings.scale);
		
		sb.draw(BlackMageMod.getTexture("img/character/player/temp.png"), x, y, 0, 0, 538f, 800f, 0.3f * Settings.scale, 0.3f * Settings.scale, 0.0f, 0, 0, 538, 800, false, false);
		
		effectFront.update();
		effectFront.render(sb);
		
		effectHand.update();
		effectHand.render(sb);
	}
	
	public static CharSelectInfo getLoadout() {
		CharSelectInfo selectInfo = new CharSelectInfo(
				"Black Mage",
				"Use the power of fire and ice to clear the tower.",
				START_HP,
				START_HP,
				99,
				5,
				EnumPatch.BLACK_MAGE_CLASS,
				getStartingRelics(),
				getStartingDeck(),
				false);
		return selectInfo;
	}
	
	public static ArrayList<String> getStartingDeck(){
		ArrayList<String> startingDeck = new ArrayList<String>();
		
		startingDeck.add("strike_ice");
		startingDeck.add("strike_ice");
		startingDeck.add("strike_ice");
		startingDeck.add("strike_fire");
		startingDeck.add("strike_fire");
		startingDeck.add("strike_fire");
		startingDeck.add("defend_bm");
		startingDeck.add("defend_bm");
		startingDeck.add("defend_bm");
		startingDeck.add("defend_bm");
		startingDeck.add("defend_bm");
		startingDeck.add("Conversion");
		return startingDeck;
	}
	
	public static ArrayList<String> getStartingRelics(){
		ArrayList<String> relics = new ArrayList<String>();
		
		relics.add("SpellBook");
		
		return relics;
	}
	
	protected void initStartDeck() {
		ArrayList<String> cards = BlackMageCharacter.getStartingDeck();
		for(String s : cards) {
			this.masterDeck.addToTop(CardLibrary.getCard(s).makeCopy());
		}
	}
	
	protected void initStartRelics() {
		ArrayList<String> relics = BlackMageCharacter.getStartingRelics();
		
		int index = 0;
		for(String s : relics) {
			RelicLibrary.getRelic(s).makeCopy().instantObtain(this, index, false);
			index++;
		}
		
		AbstractDungeon.relicsToRemoveOnStart.addAll(relics);
	}

	public ArrayList<Texture> setEnergyLayers(String[] strings) {
		ArrayList<Texture> textures = new ArrayList<Texture>();
		
		for(String s : strings) {
			textures.add(new Texture(Gdx.files.internal(s)));
		}
		
		return textures;
	}
}
