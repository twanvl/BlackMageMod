package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;
import blackmage.powers.GainAshNextTurnPower;

public class HotCoals extends AbstractCustomCardWithType {

	public static final String ID = "HotCoals";
	private static final String NAME = "Hot Coals";
	private static final String IMG = "img/cards/icons/coals.png";
	private static final String DESCRIPTION = "If you have Fire, next turn gain 1 Ash per stack of Fire. NL Lose Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;
	
	private static final int COST = 1;
	private static final int MAGIC = 1;
	
	private int bonus = 0;
	
	public HotCoals() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseMagicNumber = MAGIC;
		this.magicNumber = MAGIC;
		this.bonus = 0;
	}
	
	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}

	@Override
	public AbstractCard makeCopy() {
		return new HotCoals();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.upgradeName();
			bonus = 1;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("bm_fire_power")) {
			int amt = p.getPower("bm_fire_power").amount + bonus;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GainAshNextTurnPower(p, amt), amt));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
		}
	}
}
