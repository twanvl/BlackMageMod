package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public abstract class AbstractMulitTypeCard extends CustomCard {

	public AbstractMulitTypeCard(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardColor color, CardRarity rarity, CardTarget target, int cardPool) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target, cardPool);
	}

	@Override
	public AbstractCard makeCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {
		// TODO Auto-generated method stub

	}
	
	public abstract void optionFire();
	
	public abstract void optionIce();
	
	public abstract void optionNeutral();
	
	public void applyPowers() {
		super.applyPowers();
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			optionIce();
		} else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			optionFire();
		} else {
			optionNeutral();
		}
	}
	
	public void calculateCardDamage(AbstractMonster arg0) {
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			optionIce();
		} else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			optionFire();
		} else {
			optionNeutral();
		}
	}

}
