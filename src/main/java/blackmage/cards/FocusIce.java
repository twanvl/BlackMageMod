package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class FocusIce extends AbstractCustomCardWithType {
	
	public static final String ID = "FocusIce";
	private static final String NAME = "Focus: Ice";
	private static final String IMG = "img/cards/icons/conversion.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[1];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[1];
	private static final String DESCRIPTION = "Exhaust all Fire cards. NL Exhaust.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 2;
	
	public FocusIce() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		this.exhaust = true;
	}

	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new FocusFire();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FocusFire();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(1);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(AbstractCard card : p.drawPile.group) {
			if(card instanceof AbstractCustomCardWithType) {
				AbstractCustomCardWithType custCard = (AbstractCustomCardWithType)card;
				if(custCard.colorType == AbstractCustomCardWithType.CardColorType.FIRE) {
					AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.drawPile));;
				}
			}
		}
		
		for(AbstractCard card : p.hand.group) {
			if(card instanceof AbstractCustomCardWithType) {
				AbstractCustomCardWithType custCard = (AbstractCustomCardWithType)card;
				if(custCard.colorType == AbstractCustomCardWithType.CardColorType.FIRE) {
					AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.hand));;
				}
			}
		}
		
		for(AbstractCard card : p.discardPile.group) {
			if(card instanceof AbstractCustomCardWithType) {
				AbstractCustomCardWithType custCard = (AbstractCustomCardWithType)card;
				if(custCard.colorType == AbstractCustomCardWithType.CardColorType.FIRE) {
					AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, p.discardPile));;
				}
			}
		}
	}
}
