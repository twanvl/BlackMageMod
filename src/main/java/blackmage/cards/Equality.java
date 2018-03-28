package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class Equality extends CustomCard{
	
	public static final String ID = "Equality";
	private static final String NAME = "Equality";
	private static final String IMG = "img/cards/icons/equality.png";
	private static final String DESCRIPTION = "Gain !B! block. NL Draw !M! card. NL Remove Fire and Ice.";
	private static final String UPDATE_DESC = "Gain !B! block. NL Draw !M! cards. NL Remove Fire and Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 1;
	private static final int DEFEND = 8;
	private static final int MAGIC = 1;
	
	public Equality() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		
		this.baseBlock = DEFEND;
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Equality();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.rawDescription = UPDATE_DESC;
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(p.hasPower("bm_ice_power"))
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_ice_power"));
		if(p.hasPower("bm_fire_power"))
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
		BlackMageMod.resetOrbColor((CustomPlayer)p);
		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
	}
}
