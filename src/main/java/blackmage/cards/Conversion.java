package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;
import blackmage.powers.IcePower;

public class Conversion extends CustomCard {
	
	public static final String ID = "Conversion";
	private static final String NAME = "Conversion";
	private static final String IMG = "img/cards/icons/conversion.png";
	private static final String DESCRIPTION = "Exchange Fire and Ice.";
	private static final String UPGRADE_DESC = DESCRIPTION + " Gain an extra Fire or Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private static final int COST = 0;
	private static final int MAGIC = 0;
	
	public Conversion() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Conversion();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.rawDescription = UPGRADE_DESC;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		int iceAmount = 0;
		int fireAmount = 0;
		if(p.hasPower("bm_ice_power")) {
			iceAmount = p.getPower("bm_ice_power").amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_ice_power"));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, iceAmount), iceAmount));
		}
		if(p.hasPower("bm_fire_power")) {
			fireAmount = p.getPower("bm_fire_power").amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, fireAmount), fireAmount));
		}
		
		if(this.magicNumber > 0) {
			if(fireAmount > 0)
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 1), 1));
			if(iceAmount > 0)
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
		}
	}
}
