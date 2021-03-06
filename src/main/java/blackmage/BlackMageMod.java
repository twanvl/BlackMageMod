package blackmage;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomPlayer;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import blackmage.cards.*;
import blackmage.character.player.BlackMageCharacter;
import blackmage.effects.ColorFadeEffect;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;
import blackmage.powers.IcePower;
import blackmage.relics.SpellBook;

@SpireInitializer
public class BlackMageMod implements PostInitializeSubscriber, EditCardsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber {
	
	private static final String MOD_NAME = "Black Mage";
	private static final String AUTHOR = "Blank";
	private static final String DESCRIPTION = "v0.0.1 NL Adds a new character based around the idea of a mage.";

	public static Color MULTI = new Color(1f, 0f, 0f, 1f);
	public static Color ORANGE = new Color(1f, 100f / 255f, 0f, 1f);
	public static Color LIGHTBLUE = new Color(0f, 180f / 255f, 1f, 1f);
	public static Color DARKCYAN = new Color(0f, 120f / 255f, 1f, 1f);
	
	private static ColorFadeEffect multiEffect = new ColorFadeEffect(ORANGE, LIGHTBLUE, MULTI);
	public static ColorFadeEffect fireEffect = new ColorFadeEffect(Color.RED, Color.YELLOW, Color.ORANGE);
	public static ColorFadeEffect iceEffect = new ColorFadeEffect(Color.CYAN, Color.BLUE, DARKCYAN);
	
	public static HashMap<String, Texture> imgMap;
	
	static {
		imgMap = new HashMap<String, Texture>();
	}
	
	public static void updateColor() {
		MULTI.set(multiEffect.getColor(true));
	}
	
	public static void applyRandomIceFirePower(int amount) {
		AbstractPlayer p = AbstractDungeon.player;
		AbstractPower power = null;
		Random rand = new Random();
		int r = rand.random(1);
		
		if(p.hasPower("bm_ice_power")) {
			power = new IcePower(p, amount);
		} else if(p.hasPower("bm_fire_power")) {
			power = new FirePower(p, amount);
		} else {
			if(r == 0)
				power = new IcePower(p, amount);
			else
				power = new FirePower(p, amount);
		}		
		
		if(power != null)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, amount));
	}
	
	//card base images
	public static final String[] ATTACK_BG = {
		"img/cards/small/attack_normal.png",
		"img/cards/small/attack_ice.png",
		"img/cards/small/attack_fire.png",
		"img/cards/small/attack_dark.png"
	};
	public static final String[] SKILL_BG = {
		"img/cards/small/skill_normal.png",
		"img/cards/small/skill_ice.png",		
		"img/cards/small/skill_fire.png",
		"img/cards/small/skill_dark.png"
	};
	public static final String[] POWER_BG = {
		"img/cards/small/power_normal.png",
		"img/cards/small/power_ice.png",
		"img/cards/small/power_fire.png",
		"img/cards/small/power_dark.png"
	};
	
	//card base portrait images
	public static final String[] ATTACK_BG_P = {
		"img/cards/portrait/attack_normal.png",
		"img/cards/portrait/attack_ice.png",
		"img/cards/portrait/attack_fire.png",
		"img/cards/portrait/attack_dark.png"
	};
	public static final String[] SKILL_BG_P = {
		"img/cards/portrait/skill_normal.png",
		"img/cards/portrait/skill_ice.png",		
		"img/cards/portrait/skill_fire.png",
		"img/cards/portrait/skill_dark.png"
	};
	public static final String[] POWER_BG_P = {
		"img/cards/portrait/power_normal.png",
		"img/cards/portrait/power_ice.png",
		"img/cards/portrait/power_fire.png",
		"img/cards/portrait/power_dark.png"
	};
	
	//energy orb images
	private static final String ENERGY_ORB = "img/cards/small/orb.png";
	private static final String ENERGY_ORB_P = "img/cards/portrait/orb.png";
	
	//character data
	private static final String BLACK_MAGE_ICON = "img/character/button.png";
	private static final String BLACK_MAGE_PORTRAIT = "img/character/portrait.png";
	
	public static final String BLACK_MAGE_SHOULDER_1 = "img/character/player/shoulder.png";
	public static final String BLACK_MAGE_SHOULDER_2 = "img/character/player/shoulder2.png";
	public static final String BLACK_MAGE_CORPSE = "img/character/player/corpse.png";
	public static final String BLACK_MAGE_SKELETON_ATLAS = "img/character/player/idle/skeleton.atlas";
	public static final String BLACK_MAGE_SKELETON_JSON = "img/character/player/idle/skeleton.json";
	
	public static Texture getOrbBlueBG() {
		return getTexture("img/character/orb/enabled/layer1-blue.png");
	}
	
	public static Texture getOrbRedBG() {
		return getTexture("img/character/orb/enabled/layer1-red.png");
	}
	
	public static Texture getOrbMultiBG() {
		return getTexture("img/character/orb/enabled/layer1.png");
	}
	
	public static Texture getTexture(String textureString) {
		if(imgMap.get(textureString) == null) {
			loadTexture(textureString);
		}
		
		return imgMap.get(textureString);
	}

	private static void loadTexture(String textureString) {
		System.out.println("BlackMageMod | Loading Texture: " + textureString);
		imgMap.put(textureString, new Texture(textureString));
	}
	
	public BlackMageMod() {
		BaseMod.subscribe(this);
	}
	
	public static void initialize() {
		
		BaseMod.addColor(
				EnumPatch.BLACK_MAGE, 
				MULTI, 
				MULTI,
				MULTI, 
				MULTI, 
				MULTI, 
				MULTI, 
				MULTI, 
				ATTACK_BG[0], 
				SKILL_BG[0],
				POWER_BG[0],
				ENERGY_ORB, 
				ATTACK_BG_P[0], 
				SKILL_BG_P[0],
				POWER_BG_P[0],
				ENERGY_ORB_P
		);
		
		@SuppressWarnings("unused")
		BlackMageMod blm = new BlackMageMod();
	}
	
	public void receivePostInitialize() {
		Texture badgeTexture = new Texture(Gdx.files.internal("img/mod-badge.png"));
		BaseMod.registerModBadge(badgeTexture, MOD_NAME, AUTHOR, DESCRIPTION, null);
		
		String jsonString = Gdx.files.internal("local/relic-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, jsonString);
		
		RelicLibrary.add(new SpellBook());
		
		//BaseMod.addPotion(UnleashPotion.class, Color.BLUE, Color.CYAN, Color.GRAY, UnleashPotion.ID);
		
		//BaseMod.addPower(DeathstrokePower.class, "bm_deathstroke_power");
	}
	
	public void receiveEditKeywords() {
		String[] iceNames = {"ice"};
		String[] fireNames = {"fire"};
		String[] freezeNames = {"freeze"};
		String[] shadowNames = {"dark"};
		String[] unleashNames = {"unleash"};
		String[] metallicizeNames = {"metallicize"};
		String[] vitalityNames = {"vitality"};
		String[] invigorateNames = {"invigorate"};
		String[] luckyNames = {"lucky"};
		
		BaseMod.addKeyword(iceNames, "A buff that increases the damage of the next #bIce type attack card.");
		BaseMod.addKeyword(fireNames, "A buff that increases the damage of the next #rFire type attack card.");
		BaseMod.addKeyword(shadowNames, "A type of damage that ignores block.");
		BaseMod.addKeyword(freezeNames, "Buffs a card based on what type it is.");
		BaseMod.addKeyword(unleashNames, "No longer loose Ice or Fire stacks.");
		BaseMod.addKeyword(metallicizeNames, "Gain block at the end of your turn.");
		BaseMod.addKeyword(vitalityNames, "Draw 1 card at the beginning of your turn.");
		BaseMod.addKeyword(invigorateNames, "Gain 1 energy at the beginning or your turn.");
		BaseMod.addKeyword(luckyNames, "After you use an attack, the next attack has a 25% chance of dealing 3x damage.");
	}
	
	public void receiveEditCharacters() {
		BaseMod.addCharacter(BlackMageCharacter.class, "Black Mage", 
				"Black Mage class string", EnumPatch.BLACK_MAGE, 
				"Black Mage", BLACK_MAGE_ICON, BLACK_MAGE_PORTRAIT, 
				EnumPatch.BLACK_MAGE_CLASS);
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new IceStrike()); //Attack
		BaseMod.addCard(new FireStrike()); //Attack
		BaseMod.addCard(new Defend_BlackMage()); //Skill
		BaseMod.addCard(new Conversion()); //Skill
		BaseMod.addCard(new Ash()); //Attack
		
		//COMMON
		BaseMod.addCard(new SnowWall()); //Skill
		BaseMod.addCard(new FlameWall()); //Skill
		BaseMod.addCard(new Equality()); //Skill
		BaseMod.addCard(new FireBlast()); //Attack
		BaseMod.addCard(new IceBlast()); //Attack
		BaseMod.addCard(new Snowflake());//Skill
		BaseMod.addCard(new Ember()); //Skill
		BaseMod.addCard(new SnowStorm()); //Attack
		BaseMod.addCard(new Firestorm()); //Attack
		BaseMod.addCard(new Frostbite()); //Skill
		BaseMod.addCard(new WyldFire()); //Skill
		BaseMod.addCard(new Distract()); //Skill
		
		//UNCOMMON
		BaseMod.addCard(new TemperatureShock()); //Attack
		BaseMod.addCard(new MagesSong()); //Skill
		BaseMod.addCard(new Stasis()); //Power
		BaseMod.addCard(new ShadowWall()); //Attack
		BaseMod.addCard(new Swiftcast()); //Skill
		BaseMod.addCard(new EnchantedRobes());
		BaseMod.addCard(new CoolingWind());//Attack
		BaseMod.addCard(new WarmingFlame());//Attack
		BaseMod.addCard(new TabletOfKnowledge());//Skill
		BaseMod.addCard(new AncientScroll());//Skill
		BaseMod.addCard(new HotCoals()); //Skill
		BaseMod.addCard(new SnowFortress()); //Skill
		BaseMod.addCard(new Cauterize()); //Skill
		BaseMod.addCard(new PrismStrike()); //Attack
		BaseMod.addCard(new Scorch()); //Power
		BaseMod.addCard(new Freeze()); //Power
		
		//SHADOW PACK
		BaseMod.addCard(new ShadowStrike()); //Attack
		BaseMod.addCard(new ShadowWall()); //Attack
		BaseMod.addCard(new CursedIce()); //Attack
		BaseMod.addCard(new BlackFire()); //Attack
		BaseMod.addCard(new DarkArmor()); //Power
		BaseMod.addCard(new BookOfShadows()); //Skill
		BaseMod.addCard(new Rend()); //Attack
		//THUNDER PACK
			//
		
		//RARE
		BaseMod.addCard(new SheerCold()); //Skill
		BaseMod.addCard(new Unleash()); //Power
		BaseMod.addCard(new Doublecast()); //Skill
		BaseMod.addCard(new Incinerate()); //Attack
		BaseMod.addCard(new Destiny()); //Skill
	}
	
	public static void setOrbColor(AbstractPower power, CustomPlayer player, boolean isUpdated) {
		@SuppressWarnings("unchecked")
		ArrayList<Texture> energyLayers = (ArrayList<Texture>)ReflectionHacks.getPrivate(player, CustomPlayer.class, "energyLayers");
		
		if (power.ID == "bm_ice_power") {
			energyLayers.set(0, getOrbBlueBG());
		}
		if (power.ID == "bm_fire_power") {
			energyLayers.set(0, getOrbRedBG());
		}
		
		if(isUpdated) {
			AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
			if(EnergyPanel.totalCount > 0)
				EnergyPanel.energyVfxTimer = 2.0f;
		}
	}
	
	public static void resetOrbColor(CustomPlayer player) {
		@SuppressWarnings("unchecked")
		ArrayList<Texture> energyLayers = (ArrayList<Texture>)ReflectionHacks.getPrivate(player, CustomPlayer.class, "energyLayers");
		energyLayers.set(0, getOrbMultiBG());
		
		if(EnergyPanel.totalCount > 0) {
			EnergyPanel.energyVfxTimer = 2.0f;
			AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
		}
	}
}
