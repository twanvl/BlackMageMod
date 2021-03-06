package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class Destiny extends CustomCard {
	
	public static final String ID = "Destiny";
	private static final String NAME = "Destiny";
	private static final String IMG = "img/cards/icons/destiny.png";
	private static final String DESCRIPTION = "Gain [B] [B] . Remove Ice and Fire. Exhaust.";
	private static final String DESCRIPTION2 = "Gain [B] [B] . Remove Ice and Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private static final int COST = 0;
	private static final int MAGIC = 2;

	public Destiny() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		
		this.exhaust = true;
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Destiny();
	}
	
	

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.upgradeName();
			this.exhaust = false;
			this.rawDescription = DESCRIPTION2;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		if(p.hasPower("bm_ice_power"))
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_ice_power"));
		if(p.hasPower("bm_fire_power"))
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
		BlackMageMod.resetOrbColor((CustomPlayer)p);
		
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(magicNumber));
	}

}
