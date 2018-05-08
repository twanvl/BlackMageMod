package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;
import blackmage.powers.UnleashPower;

public class Unleash extends CustomCard {
	
	public static final String ID = "Unleash";
	private static final String NAME = "Unleash";
	private static final String IMG = "img/cards/icons/unleash.png";
	private static final String DESCRIPTION = "Gain Unleash.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 2;

	public Unleash() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Unleash();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
			//this.isInnate = true;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UnleashPower(p)));
	}

}
