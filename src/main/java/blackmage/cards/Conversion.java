package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCardWithRender;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;
import blackmage.powers.IcePower;

public class Conversion extends CustomCardWithRender {
	
	public static final String ID = "Conversion";
	private static final String NAME = "Conversion";
	private static final String IMG = "img/cards/icons/conversion.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[0];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[0];
	private static final String DESCRIPTION = "Exchange Fire and Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private static final int COST = 1;
	
	public Conversion() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Conversion();
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
		int iceAmount = 0;
		int fireAmount = 0;
		if(p.hasPower("bm_ice_power")) {
			iceAmount = p.getPower("bm_ice_power").amount;
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "bm_ice_power"));
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new FirePower(p, iceAmount), iceAmount));
		}
		if(p.hasPower("bm_fire_power")) {
			fireAmount = p.getPower("bm_fire_power").amount;
			AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new IcePower(p, fireAmount), fireAmount));
		}
	}
}
