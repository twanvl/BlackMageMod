package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;
import blackmage.powers.DarkArmorPower;

public class DarkArmor extends AbstractCustomCardWithType {
	
	public static final String ID = "DarkArmor";
	private static final String NAME = "Dark Armor";
	private static final String IMG = "img/cards/icons/darkarmor.png";
	private static final String DESCRIPTION = "Mitigate 50% of the damage from !M! attacks.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.DARK;

	private static final int COST = 2;
	private static final int MAGIC = 3;
	
	public DarkArmor() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);

		this.baseMagicNumber = MAGIC;
		this.magicNumber = MAGIC;
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
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(2);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DarkArmorPower(p, magicNumber), magicNumber));
	}

}
