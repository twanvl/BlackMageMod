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
import blackmage.cards.Conversion;
import blackmage.cards.Defend_BlackMage;
import blackmage.cards.FireStrike;
import blackmage.cards.IceStrike;
import blackmage.particles.Particle;
import blackmage.particles.ParticleEffect;
import blackmage.patches.EnumPatch;
import blackmage.relics.SpellBook;

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
	private double counter = 0.0f;
	
	private ParticleEffect effectFront = new ParticleEffect(
			new Rectangle(370, 310, 200f * Settings.scale, 5 * Settings.scale), 
			0.8f,                            
			4,                              
			new Vector2(-0.004f, 0.25f),    
			new Particle(40, BlackMageMod.MULTI, BlackMageMod.getTexture("img/particles/particle.png"))
			);
	
	private ParticleEffect effectHand = new ParticleEffect(
			new Rectangle(425, 430, 5 * Settings.scale, 5 * Settings.scale),
			0.75f,
			4,
			new Vector2(0.0f, 0.05f),
			new Particle(40, BlackMageMod.MULTI, BlackMageMod.getTexture("img/particles/particle.png"))
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
		
		System.out.println("blackmage ctor : finish");
	}
	
	private void doParticleEffects(SpriteBatch sb, int x, int y) {
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			effectFront.setParticleColor(BlackMageMod.iceEffect.getColor(true));
			effectHand.setParticleColor(BlackMageMod.iceEffect.getColor(true));
			effectFront.setParticleTexture(BlackMageMod.getTexture("img/particles/particle-snow.png"));
			effectHand.setParticleTexture(BlackMageMod.getTexture("img/particles/particle-snow.png"));
		}else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			effectFront.setParticleColor(BlackMageMod.fireEffect.getColor(true));
			effectHand.setParticleColor(BlackMageMod.fireEffect.getColor(true));
			effectFront.setParticleTexture(BlackMageMod.getTexture("img/particles/particle-fire.png"));
			effectHand.setParticleTexture(BlackMageMod.getTexture("img/particles/particle-fire.png"));
		}else {
			effectFront.setParticleColor(BlackMageMod.MULTI);
			effectHand.setParticleColor(BlackMageMod.MULTI);
			effectFront.setParticleTexture(BlackMageMod.getTexture("img/particles/particle.png"));
			effectHand.setParticleTexture(BlackMageMod.getTexture("img/particles/particle.png"));
		}
		
		effectFront.moveSpawnRegion(x - (40f * Settings.scale), y - (10f * Settings.scale));
		effectHand.moveSpawnRegion(x + (15f * Settings.scale), y + (110f * Settings.scale));
		
		effectFront.update();
		effectFront.render(sb);
		
		effectHand.update();
		effectHand.render(sb);
	}
	
	@SuppressWarnings("unused")
	public void renderPlayerImage(SpriteBatch sb) {
		if(!(AbstractDungeon.player instanceof BlackMageCharacter))
			return;
		
		sb.setColor(1, 1, 1, 1);
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		
		counter += Gdx.graphics.getDeltaTime();
		
		if(counter > 100.0 && Math.sin(counter) > 0.9999) {
			counter = Math.PI / 2;
		}
		
		int x = (int) (Math.round(410 * Settings.scale));
		int y = (int) (Math.round(330 * Settings.scale) + (12 * Math.sin(counter)));
		
		sb.draw(BlackMageMod.getTexture("img/character/player/temp.png"), x, y, 0, 0, 538f, 800f, 0.3f * Settings.scale, 0.3f * Settings.scale, 0.0f, 0, 0, 538, 800, false, false);
		
		doParticleEffects(sb, x, y);
	}
	
	public static CharSelectInfo getLoadout() {
		CharSelectInfo selectInfo = new CharSelectInfo(
				"Black Mage",
				"Use the power of fire and ice to clear the tower.",
				START_HP,
				START_HP,
				0, //Possibly have 1 orb to represent type
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
		
		startingDeck.add(IceStrike.ID);
		startingDeck.add(IceStrike.ID);
		startingDeck.add(IceStrike.ID);
		startingDeck.add(FireStrike.ID);
		startingDeck.add(FireStrike.ID);
		startingDeck.add(FireStrike.ID);
		startingDeck.add(Defend_BlackMage.ID);
		startingDeck.add(Defend_BlackMage.ID);
		startingDeck.add(Defend_BlackMage.ID);
		startingDeck.add(Defend_BlackMage.ID);
		startingDeck.add(Defend_BlackMage.ID);
		startingDeck.add(Conversion.ID);
		return startingDeck;
	}
	
	public static ArrayList<String> getStartingRelics(){
		ArrayList<String> relics = new ArrayList<String>();
		
		relics.add(SpellBook.ID);
		
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
