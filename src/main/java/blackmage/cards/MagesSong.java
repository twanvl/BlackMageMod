package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class MagesSong extends CustomCard {
	public static final String ID = "MagesSong";
	private static final String NAME = "Mage's Song";
	private static final String IMG = "img/cards/icons/magesong.png";
	
	private static final String DESCRIPTION = "Double elemental buff.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 1;
	
	public MagesSong() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		//this.isExhausted = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new MagesSong();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		if(p.hasPower("bm_ice_power")) {
			AbstractPower power = p.getPower("bm_ice_power");
			power.stackPower(power.amount);
			power.flash();
		}
		if(p.hasPower("bm_fire_power")) {
			AbstractPower power = p.getPower("bm_fire_power");
			power.stackPower(power.amount);
			power.flash();
		}
	}
	
	
}
