package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class Defend_BlackMage extends CustomCard {
	public static final String ID = "defend_bm";
	private static final String NAME = "Defend";
	private static final String IMG = "img/cards/icons/defend-normal.png";
	private static final String DESCRIPTION = "Gain !M! block.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private static final int COST = 1;
	private static final int MAGIC = 5;
	
	public Defend_BlackMage() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 0);
		this.baseMagicNumber = MAGIC;
		this.magicNumber = this.baseMagicNumber;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Defend_BlackMage();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(3);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.magicNumber));
	}
}
