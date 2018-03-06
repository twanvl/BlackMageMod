package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RetainCardPower;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class Stasis extends CustomCard {
	
	public static final String ID = "Stasis";
	private static final String NAME = "Stasis";
	private static final String IMG = "img/cards/icons/stasis.png";
	
	private static final String DESCRIPTION = "At the end of your turn, retain up to !M! card.";
	private static final String UPGRADE_DESCRIPTION = "At the end of your turn, retain up to !M! cards.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 1;
	private static final int MAGIC = 1;
	
	public Stasis() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		
		this.baseMagicNumber = MAGIC;
		this.magicNumber = MAGIC;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Stasis();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded)
	    {
			upgradeName();
	      	upgradeMagicNumber(1);
	      	this.rawDescription = UPGRADE_DESCRIPTION;
	      	initializeDescription();
	    }
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RetainCardPower(p, this.magicNumber), this.magicNumber));
	}

}
