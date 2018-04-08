package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;

public class DarkArmor extends AbstractCustomCardWithType {
	
	public static final String ID = "DarkArmor";
	private static final String NAME = "Dark Armor";
	private static final String IMG = "img/cards/icons/snowwall.png";
	private static final String DESCRIPTION = "Gain !B! block. Gain 3 Dark Armor.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.DARK;

	private static final int COST = 2;
	private static final int BLOCK = 8;
	
	public DarkArmor() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		this.block = BLOCK;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}

	@Override
	public AbstractCard makeCopy() {
		return new DarkArmor();
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub

	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
	}

}
