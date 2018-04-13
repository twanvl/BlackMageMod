package blackmage.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class AncientScroll extends AbstractMulitTypeCard {
	
	public static final String ID = "AncientScroll";
	private static final String NAME = "Ancient Scroll";
	private static final String IMG = "img/cards/icons/magesong.png";
	private static final String[] DESCRIPTIONS = {
			"Gain either !M! Ice or !M! Fire.", 
			"Increase Ice by !M!.",
			"Increase Fire by !M!."};
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private static final int COST = 2;
	private static final int MAGIC = 3;

	public AncientScroll() {
		super(ID, NAME, IMG, COST, DESCRIPTIONS[0], TYPE, COLOR, RARITY, TARGET, 1);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new AncientScroll();
	}
	

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(2);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		BlackMageMod.applyRandomIceFirePower(magicNumber, true);
	}

	@Override
	public void optionIce() {
		this.setOrbTexture("img/cards/small/orb-ice.png", "img/cards/portrait/orb-ice.png");
		this.rawDescription = DESCRIPTIONS[1];
		this.initializeDescription();
	}

	@Override
	public void optionFire() {
		this.setOrbTexture("img/cards/small/orb-fire.png", "img/cards/portrait/orb-fire.png");
		this.rawDescription = DESCRIPTIONS[2];
		this.initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.setOrbTexture("img/cards/small/orb.png", "img/cards/portrait/orb.png");
		this.rawDescription = DESCRIPTIONS[0];
		this.initializeDescription();
	}

}
