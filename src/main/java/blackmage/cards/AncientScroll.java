package blackmage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class AncientScroll extends AbstractMulitTypeCard {
	
	public static final String ID = "AncientScroll";
	private static final String NAME = "Ancient Scroll";
	private static final String IMG = "img/cards/icons/scroll.png";
	private static final String[] DESCRIPTIONS = {
			"Gain either !M! Ice or !M! Fire.", 
			"Increase Ice by !M!.",
			"Increase Fire by !M!."};
	private static final String DESCRIPTION = "Increase Fire or Ice. NL Hover to show conditional effects.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

	private List<TooltipInfo> tips;
	
	private static final int COST = 1;
	private static final int MAGIC = 3;

	public AncientScroll() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		
		tips = new ArrayList<TooltipInfo>();
		
		tips.add(new TooltipInfo("Effects", "#bIce: NL Increase Ice by 3. NL NL #rFire: NL Increase Fire by 3. NL NL #yNeither: NL Gain 3 Ice or Fire."));
		
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
			this.upgradeBaseCost(0);
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return tips;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		BlackMageMod.applyRandomIceFirePower(magicNumber);
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
