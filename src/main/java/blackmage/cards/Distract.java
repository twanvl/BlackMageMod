package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class Distract extends CustomCard{
	
	public static final String ID = "Distract";
	private static final String NAME = "Distract";
	private static final String IMG = "img/cards/icons/defend-normal.png";
	private static final String DESCRIPTION = "Discard a card. Apply !M! Weak.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private static final int COST = 0;
	private static final int MAGIC = 1;
	
	public Distract() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Distract();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, 1, false));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, m, new WeakPower(m, magicNumber, false), magicNumber));
	}

}
