package blackmage.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import blackmage.BlackMageMod;

public abstract class AbstractCustomCardWithType extends CustomCard{

	public enum CardColorType
	{
			ICE, FIRE, DARK;
			CardColorType() {}
	}
	
	public CardColorType colorType;
	private boolean willApplyPowers;
	
	public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardColorType damageType, boolean willApplyPowers) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
		colorType = damageType;
		
		assignOrbTexture();
		assignBGTexture();
		assignBannerTexture();
	}
	
	public AbstractCustomCardWithType(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, CardColorType damageType) {
		this(id, name, img, cost, rawDescription, type, color, rarity, target, damageType, true);
	}
	
	private void assignBannerTexture() {
		
	}
	
	private void assignOrbTexture() {
		switch(colorType) {
		case ICE:
			this.setOrbTexture("img/cards/small/orb-ice.png", "img/cards/portrait/orb-ice.png");
			break;
		case FIRE:
			this.setOrbTexture("img/cards/small/orb-fire.png", "img/cards/portrait/orb-fire.png");
			break;
		case DARK:
			this.setOrbTexture("img/cards/small/orb-dark.png", "img/cards/portrait/orb-dark.png");
			break;
		}
	}
	
	private void assignBGTexture() {
		String[] textureListPointer;
		String[] portraitListPointer;
		int indexPointer = 0;
		
		switch(type) {
		case ATTACK:
			textureListPointer = BlackMageMod.ATTACK_BG;
			portraitListPointer = BlackMageMod.ATTACK_BG_P;
			break;
		default:
		case SKILL:
			textureListPointer = BlackMageMod.SKILL_BG;
			portraitListPointer = BlackMageMod.SKILL_BG_P;
			break;
		case POWER:
			textureListPointer = BlackMageMod.POWER_BG;
			portraitListPointer = BlackMageMod.POWER_BG_P;
			break;
		}
		
		switch(colorType) {
		case ICE:
			indexPointer = 1;
			break;
		case FIRE:
			indexPointer = 2;
			break;
		case DARK:
			indexPointer = 3;
			break;
		}
		
		this.setBackgroundTexture(textureListPointer[indexPointer], portraitListPointer[indexPointer]);
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		
		if(!this.willApplyPowers)
			return;
		
		if(this.damage != 0) {
			int modifier = 0;
			
			switch(colorType) {
			case ICE:
				if(AbstractDungeon.player.hasPower("bm_ice_power"))
					modifier += AbstractDungeon.player.getPower("bm_ice_power").amount;
				break;
			case FIRE:
				if(AbstractDungeon.player.hasPower("bm_fire_power"))
					modifier += AbstractDungeon.player.getPower("bm_fire_power").amount;
				break;
			case DARK:
				break;
			default:
				break;
			}
			
			if(modifier != 0) this.isDamageModified = true;
			
			this.damage += modifier;
		}
		if(this.block != 0) {
			int modifier = 0;
			
			switch(colorType) {
			case ICE:
				if(AbstractDungeon.player.hasPower("bm_ice_power"))
					modifier += AbstractDungeon.player.getPower("bm_ice_power").amount;
				break;
			case FIRE:
				if(AbstractDungeon.player.hasPower("bm_fire_power"))
					modifier += AbstractDungeon.player.getPower("bm_fire_power").amount;
				break;
			case DARK:
				break;
			default:
				break;
			}
			
			if(modifier != 0) this.isBlockModified = true;
			
			this.block += modifier;
		}
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		super.calculateCardDamage(arg0);
		
		int modifier = 0;
		
		if(!this.willApplyPowers)
			return;
		
		if(this.damage != 0) {
			
			switch(colorType) {
			case ICE:
				if(AbstractDungeon.player.hasPower("bm_ice_power"))
					modifier += AbstractDungeon.player.getPower("bm_ice_power").amount;
				break;
			case FIRE:
				if(AbstractDungeon.player.hasPower("bm_fire_power"))
					modifier += AbstractDungeon.player.getPower("bm_fire_power").amount;
				break;
			case DARK:
				break;
			default:
				break;
			}
			
			if(modifier != 0) this.isDamageModified = true;
			
			this.damage += modifier;
			
			if(this.isMultiDamage) {
				for(int i = 0; i < multiDamage.length; i++) {
					multiDamage[i] += modifier;
				}
			}
		}
		if(this.block != 0) {
			
			switch(colorType) {
			case ICE:
				if(AbstractDungeon.player.hasPower("bm_ice_power"))
					modifier += AbstractDungeon.player.getPower("bm_ice_power").amount;
				break;
			case FIRE:
				if(AbstractDungeon.player.hasPower("bm_fire_power"))
					modifier += AbstractDungeon.player.getPower("bm_fire_power").amount;
				break;
			case DARK:
				break;
			default:
				break;
			}
			
			if(modifier != 0) this.isBlockModified = true;
			
			this.block += modifier;
		}
	}
	
	public abstract AbstractCustomCardWithType getOpposite(boolean isUpgraded);
}
