package blackmage.choicecards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import basemod.abstracts.CustomCardWithRender;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class StrengthChoice extends CustomCardWithRender {

	public static final String ID = "StrengthChoise";
	private static final String NAME = "Empowering";
	private static final String IMG = "img/cards/icons/strength.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[0];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[0];
	private static final String DESCRIPTION = "Gain 5 Strength.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = -2;
	
	public StrengthChoice() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 0);
		dontTriggerOnUseCard = true;
	    purgeOnUse = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new StrengthChoice();
	}

	@Override
	public void upgrade() {
		//NOP
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 5), 5));
	}
}
