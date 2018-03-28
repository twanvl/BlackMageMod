package blackmage.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCardWithRender;

public abstract class AbstractCustomCardWithType extends CustomCardWithRender{

	public enum CardColorType
	{
			ICE, FIRE, DARK;
			CardColorType() {}
	}
	
	public CardColorType colorType;
	private boolean willApplyPowers;
	
	public AbstractCustomCardWithType(String id, String name, String img, String bgTexture, String bgTexture_p,
			int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
			int cardPool, CardColorType damageType) {
		super(id, name, img, bgTexture, bgTexture_p, cost, rawDescription, type, color, rarity, target, cardPool);
		colorType = damageType;
		this.willApplyPowers = true;
	}
	
	public AbstractCustomCardWithType(String id, String name, String img, String bgTexture, String bgTexture_p,
			int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
			int cardPool, CardColorType damageType, boolean willApplyPowers) {
		super(id, name, img, bgTexture, bgTexture_p, cost, rawDescription, type, color, rarity, target, cardPool);
		colorType = damageType;
		this.willApplyPowers = willApplyPowers;
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
			
			if(modifier != 0) this.isDamageModified = true;
			
			this.block += modifier;
		}
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		super.calculateCardDamage(arg0);
		
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
			
			if(this.isMultiDamage) {
				for(int i = 0; i < multiDamage.length; i++) {
					multiDamage[i] += modifier;
				}
			}
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
			
			if(modifier != 0) this.isDamageModified = true;
			
			this.block += modifier;
		}
	}
	
	public abstract AbstractCustomCardWithType getOpposite(boolean isUpgraded);
}
