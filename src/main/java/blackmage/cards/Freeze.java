package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;
import blackmage.powers.FreezePower;

public class Freeze extends AbstractCustomCardWithType {
	
	public static final String ID = "Freeze";
	private static final String NAME = "Freeze";
	private static final String IMG = "img/cards/icons/strike-ice.png";
	private static final String DESCRIPTION = "When ever you play an Ice type card. Gain !M! block.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 1;
	private static final int MAGIC = 1;
	
	public Freeze() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 0, COLOR_TYPE);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new Scorch();
		if(isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Freeze();
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
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FreezePower(p, magicNumber), magicNumber));
	}
}
