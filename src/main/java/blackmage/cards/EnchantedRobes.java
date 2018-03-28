package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class EnchantedRobes extends CustomCard {
	
	public static final String ID = "EnchantedRobes";
	private static final String NAME = "Enchanted Robes";
	private static final String IMG = "img/cards/icons/magesong.png";
	
	private static final String DESCRIPTION = "Pick 1 of three robe enchantments. Innate.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 1;
	private static final int POOL = 0;
	
	public EnchantedRobes() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, POOL);
		this.isInnate = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new EnchantedRobes();
	}

	@Override
	public void upgrade() {
		
	}

	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {
		//AbstractDungeon.actionManager.addToBottom(new EnchantedRobesAction());
	}

}
