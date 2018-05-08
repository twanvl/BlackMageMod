package blackmage.choicecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;
import blackmage.powers.Invigorate;

public class EnergizeChoice extends CustomCard {
	
	public static final String ID = "EnergizeChoise";
	private static final String NAME = "Energized";
	private static final String IMG = "img/cards/icons/invigorate.png";
	private static final String DESCRIPTION = "Gain Invigorate for 99 turns.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = -2;
	
	public EnergizeChoice() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		dontTriggerOnUseCard = true;
	    purgeOnUse = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new EnergizeChoice();
	}

	@Override
	public void upgrade() {
		//NOP
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Invigorate(p, 99), 99));
	}

}
