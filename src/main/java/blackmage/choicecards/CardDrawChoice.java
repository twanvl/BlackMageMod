package blackmage.choicecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;
import blackmage.powers.DrawCardPower;

public class CardDrawChoice extends CustomCard{
	public static final String ID = "CardDrawChoise";
	private static final String NAME = "Pocketed";
	private static final String IMG = "img/cards/icons/carddraw.png";
	private static final String DESCRIPTION = "Gain Vitality for 99 turns.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = -2;
	
	public CardDrawChoice() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		dontTriggerOnUseCard = true;
	    purgeOnUse = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new CardDrawChoice();
	}

	@Override
	public void upgrade() {
		//NOP
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawCardPower(p, 1, 99), 99));
	}

}
