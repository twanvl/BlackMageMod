package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackmage.patches.EnumPatch;

public class Swiftcast extends CustomCard {
	
	public static final String ID = "Swiftcast";
	private static final String NAME = "Swiftcast";
	private static final String IMG = "img/cards/icons/swiftcast.png";
	
	private static final String DESCRIPTION = "Make a random Ice or Fire card free this turn.";
	private static final String UPGRADE_DESC = "Make !M! random Ice or Fire cards free this turn.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 0;
	private static final int CARDS = 1;
	
	public Swiftcast() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		this.magicNumber = CARDS;
		this.baseMagicNumber = CARDS;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Swiftcast();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.rawDescription = UPGRADE_DESC;
			this.upgradeMagicNumber(1);
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		CardGroup hand = p.hand;
		CardGroup ice_fire_cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		for(AbstractCard card : hand.group) {
			if(card instanceof AbstractCustomCardWithType) {
				if(card.costForTurn != 0)
					ice_fire_cards.group.add(card);
			}
		}
		
		for(int i = 0; i < this.magicNumber; i++) {
			if(ice_fire_cards.group.size() > 0) {
				AbstractCard selected_card = ice_fire_cards.getRandomCard(true);
				selected_card.setCostForTurn(0);
				selected_card.flash();
			}
		}
	}
}
