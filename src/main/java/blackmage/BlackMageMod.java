package blackmage;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomPlayer;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import blackmage.cards.*;
import blackmage.character.player.BlackMageCharacter;
import blackmage.patches.EnumPatch;
import blackmage.relics.SpellBook;

@SpireInitializer
public class BlackMageMod implements PostInitializeSubscriber, EditCardsSubscriber, EditCharactersSubscriber {
	
	private static final String MOD_NAME = "Black Mage";
	private static final String AUTHOR = "Blank";
	private static final String DESCRIPTION = "v0.0.1 NL Adds a new character based around the idea of a mage.";
	
	private static final Color MULTI = CardHelper.getColor(255f, 255f, 255f);
	
	//card base images
	public static final String[] ATTACK_BG = {
		"img/cards/small/attack_normal.png",
		"img/cards/small/attack_ice.png",
		"img/cards/small/attack_fire.png",
		"img/cards/small/attack_shadow.png"
	};
	public static final String[] SKILL_BG = {
		"img/cards/small/skill_normal.png",
		"img/cards/small/skill_ice.png",		
		"img/cards/small/skill_fire.png"
	};
	public static final String[] POWER_BG = {
		"img/cards/small/power_normal.png",
		"img/cards/small/power_ice.png",
		"img/cards/small/power_fire.png",
	};
	
	//card base portrait images
	public static final String[] ATTACK_BG_P = {
		"img/cards/portrait/attack_normal.png",
		"img/cards/portrait/attack_ice.png",
		"img/cards/portrait/attack_fire.png",
		"img/cards/small/attack_shadow.png"
	};
	public static final String[] SKILL_BG_P = {
		"img/cards/portrait/skill_normal.png",
		"img/cards/portrait/skill_ice.png",		
		"img/cards/portrait/skill_fire.png"
	};
	public static final String[] POWER_BG_P = {
		"img/cards/portrait/power_normal.png",
		"img/cards/portrait/power_ice.png",
		"img/cards/portrait/power_fire.png",
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
	
	private static Texture orbBlueBG = null;
	private static Texture orbRedBG = null;
	private static Texture orbDefaultBG = null;
	
	public static Texture getIcePowerTexture() {
		return new Texture(Gdx.files.internal("img/powers/ice.png"));
	}
	
	public static Texture getFirePowerTexture() {
		return new Texture(Gdx.files.internal("img/powers/fire.png"));
	}
	
	public static Texture getUnleashPowerTexture() {
		return new Texture(Gdx.files.internal("img/powers/unleash.png"));
	}
	
	public static Texture getCampfireExchangeButton() {
		return new Texture(Gdx.files.internal("img/ui/exchange_button.png"));
	}
	
	public static Texture getOrbBlueBG() {
		if(orbBlueBG == null)
			orbBlueBG = new Texture("img/character/orb/enabled/layer1-blue.png");
		return orbBlueBG;
	}
	
	public static Texture getOrbRedBG() {
		if(orbRedBG == null)
			orbRedBG = new Texture("img/character/orb/enabled/layer1-red.png");
		return orbRedBG;
	}
	
	public static Texture getOrbMultiBG() {
		if(orbDefaultBG == null)
			orbDefaultBG = new Texture("img/character/orb/enabled/layer1.png");
		return orbDefaultBG;
	}
	
	public BlackMageMod() {
		BaseMod.subscribeToPostInitialize(this);
		BaseMod.subscribeToEditCharacters(this);
		BaseMod.subscribeToEditCards(this);
	}
	
	public static void initialize() {
		
		BaseMod.addColor(
				EnumPatch.BLACK_MAGE.toString(), 
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
	
		String[] iceNames = {"ice"};
		String[] fireNames = {"fire"};
		String[] freezeNames = {"freeze"};
		String[] shadowNames = {"dark"};
		
		BaseMod.addKeyword(iceNames, "A buff that increases the damage of the next #bIce type attack card.");
		BaseMod.addKeyword(fireNames, "A buff that increases the damage of the next #rFire type attack card.");
		BaseMod.addKeyword(shadowNames, "A type of damage that ignores block.");
		BaseMod.addKeyword(freezeNames, "Target is unable to attack.");
		
		RelicLibrary.add(new SpellBook());
	}
	
	public void receiveEditCharacters() {
		BaseMod.addCharacter(BlackMageCharacter.class, "Black Mage", 
				"Black Mage class string", EnumPatch.BLACK_MAGE.toString(), 
				"Black Mage", BLACK_MAGE_ICON, BLACK_MAGE_PORTRAIT, 
				EnumPatch.BLACK_MAGE_CLASS.toString());
	}

	public void receiveEditCards() {
		//BASIC
		BaseMod.addCard(new IceStrike()); //Attack
		BaseMod.addCard(new FireStrike()); //Attack
		BaseMod.addCard(new Defend_BlackMage()); //Skill
		//COMMON
		BaseMod.addCard(new SnowWall()); //Skill
		BaseMod.addCard(new FlameWall()); //Skill
		BaseMod.addCard(new Conversion()); //Skill
		BaseMod.addCard(new Equality()); //Skill
		BaseMod.addCard(new FireBlast()); //Attack
		BaseMod.addCard(new IceBlast()); //Attack
		BaseMod.addCard(new ShadowStrike()); //Attack
			//Ice Fortress
			//Fire Fortress
			//Discard card
			//More defend cards
		//UNCOMMON
		BaseMod.addCard(new Blizzard()); //Attack
		BaseMod.addCard(new Firestorm()); //Attack
		BaseMod.addCard(new TemperatureShock()); //Attack
		BaseMod.addCard(new MagesSong()); //Skill
		BaseMod.addCard(new Stasis()); //Power
		BaseMod.addCard(new ShadowWall()); //Attack
		BaseMod.addCard(new Swiftcast()); //Skill
			//Skill
		//RARE
		BaseMod.addCard(new SheerCold()); //Skill
		BaseMod.addCard(new Unleash()); //Power
			//Skill
			//Skill
			//Attack
			//Attack
			//Attack
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
