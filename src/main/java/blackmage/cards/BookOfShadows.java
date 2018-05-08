package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.actions.BookOfShadowsAction;
import blackmage.actions.BookOfShadowsFollowUpAction;
import blackmage.patches.EnumPatch;

public class BookOfShadows extends AbstractCustomCardWithType {
	
	public static final String ID = "BookOfShadows";
	private static final String NAME = "Book of Shadows";
	private static final String IMG = "img/cards/icons/badbook.png";
	private static final String DESCRIPTION = "Draw !M! cards. If any of them are dark type, put 1 Shackle in your discard pile.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = CardColorType.DARK;
	
	private static final int COST = 1;
	private static final int MAGIC = 3;
	
	public BookOfShadows() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, COLOR_TYPE);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}

	@Override
	public AbstractCard makeCopy() {
		return new BookOfShadows();
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
		AbstractDungeon.actionManager.addToBottom(new BookOfShadowsAction(p, magicNumber));
		
		AbstractDungeon.actionManager.addToBottom(new BookOfShadowsFollowUpAction());
	
		
	}

}
