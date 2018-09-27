package blackmage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;
import basemod.helpers.TooltipInfo;
import blackmage.actions.DealDamageMultiTimesAction;
import blackmage.patches.EnumPatch;

public class PrismStrike extends AbstractMulitTypeCard {
	
	public static final String ID = "Prism";
	private static final String NAME = "Prism";
	private static final String IMG = "img/cards/icons/prism.png";
	private static final String[] DESCRIPTIONS = {
			"Deal !D! ice damage !M! times.", 
			"Deal !D! fire damage !M! times.",
			"Deal !D! damage !M! times."};
	private static final String DESCRIPTION = "Deal !D! damage !M! times. Hover to show conditional effects.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private List<TooltipInfo> tips;
	
	private static final int COST = 2;
	private static final int DAMAGE = 2;
	private static final int MAGIC = 5;
	
	public PrismStrike() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		
		tips = new ArrayList<TooltipInfo>();
		
		tips.add(new TooltipInfo("Effects", 
				"#bIce: NL Deal Ice Damage. NL NL #rFire: NL Deal Fire Damage. NL NL #yNeither: NL Increase hits by 5."));
		
		this.baseDamage = DAMAGE;
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
        CardTags.addTags(this, BaseModTags.STRIKE);
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return tips;
	}

	@Override
	public AbstractCard makeCopy() {
		return new PrismStrike();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(1);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DealDamageMultiTimesAction(m, new DamageInfo(p, damage), this.magicNumber));
	}

	@Override
	public void optionFire() {
		this.setOrbTexture("img/cards/small/orb-fire.png", "img/cards/portrait/orb-fire.png");
		this.rawDescription = DESCRIPTIONS[1];
		this.initializeDescription();
		this.magicNumber = MAGIC;
		this.isMagicNumberModified = true;
		if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			this.damage += AbstractDungeon.player.getPower("bm_fire_power").amount;
			this.isDamageModified = true;
		}
	}

	@Override
	public void optionIce() {
		this.setOrbTexture("img/cards/small/orb-ice.png", "img/cards/portrait/orb-ice.png");
		this.rawDescription = DESCRIPTIONS[0];
		this.initializeDescription();
		this.magicNumber = MAGIC;
		this.isMagicNumberModified = true;
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			this.damage += AbstractDungeon.player.getPower("bm_ice_power").amount;
			this.isDamageModified = true;
		}
	}

	@Override
	public void optionNeutral() {
		this.setOrbTexture("img/cards/small/orb.png", "img/cards/portrait/orb.png");
		this.rawDescription = DESCRIPTIONS[2];
		this.initializeDescription();
		this.magicNumber = MAGIC + 5;
		this.isMagicNumberModified = true;
	}

}
