package blackmage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import basemod.helpers.TooltipInfo;
import blackmage.actions.RemoveDebuffAction;
import blackmage.patches.EnumPatch;

public class Cauterize extends AbstractMulitTypeCard {
	
	public static final String ID = "Cauterize";
	private static final String NAME = "Cauterize";
	private static final String IMG = "img/cards/icons/cauterize.png";
	
	private String EXHAUST_TEXT = " NL Exhaust.";
	private static final String DESCRIPTION = "Gain a conditional effect based on type. NL Exhaust. NL Hover to show conditional effects.";
	
	private static final AbstractCard.CardType TYPE = CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
	
	private List<TooltipInfo> tips;
	
	private static final int COST = 1;
	private static final int POOL = 1;
	
	public Cauterize() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, POOL);
		
		tips = new ArrayList<TooltipInfo>();
		
		tips.add(new TooltipInfo("Effects","#bIce: NL Gain 1 Artifact NL NL #rFire: NL Remove a debuff. NL NL #yNeither: NL Heal 5 hp."));
		
		this.exhaust = true;
	}
	
	public AbstractCard makeCopy() {
		return new Cauterize();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.exhaust = false;
			this.rawDescription = "Gain a conditional effect based on type. NL Hover to show conditional effects.";
			this.initializeDescription();
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return tips;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
		} else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveDebuffAction(p, p, 1));
		} else {
			AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 5));
		}
	}

	@Override
	public void optionIce() {
		this.setOrbTexture("img/cards/small/orb-ice.png", "img/cards/portrait/orb-ice.png");
		this.rawDescription = "Gain 1 Artifact." + this.EXHAUST_TEXT;
		this.initializeDescription();
	}

	@Override
	public void optionFire() {
		this.setOrbTexture("img/cards/small/orb-fire.png", "img/cards/portrait/orb-fire.png");
		this.rawDescription = "Remove a debuff." + this.EXHAUST_TEXT;
		this.initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.setOrbTexture("img/cards/small/orb.png", "img/cards/portrait/orb.png");
		this.rawDescription = "Heal 5 HP." + this.EXHAUST_TEXT;
		this.initializeDescription();
	}
}
