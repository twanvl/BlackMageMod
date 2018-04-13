package blackmage.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.PutOnBottomOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;

import blackmage.patches.EnumPatch;

public class TabletOfKnowledge extends AbstractMulitTypeCard {
	
	public static final String ID = "RuneOfKnowledge";
	private static final String NAME = "Rune of Knowledge";
	private static final String IMG = "img/cards/icons/rune.png";
	private static final String DESCRIPTION = "Gain 8 block. Gain an extra effect.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private List<TooltipInfo> tips;
	private boolean isInHand = false;
	
	private static final int COST = 1;
	private static final int BLOCK = 8;
	
	public TabletOfKnowledge() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		
		tips = new ArrayList<TooltipInfo>();
		
		tips.add(new TooltipInfo("Ice", "If you have Ice, shuffle a card into your draw pile."));
		tips.add(new TooltipInfo("Fire", "If you have Fire, discard a card."));
		tips.add(new TooltipInfo("Neither", "If you have Neither, gain 5 more block."));
		
		this.baseBlock = BLOCK;
	}
	
	@Override
	public boolean canUpgrade() {
		return false;
	}

	@Override
	public AbstractCard makeCopy() {
		return new TabletOfKnowledge();
	}

	@Override
	public void upgrade() {
		//NOP
	}

	@Override
	public List<TooltipInfo> getCustomTooltips() {
		if(isInHand) {
			return null;
		}
		
		return tips;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			AbstractDungeon.actionManager.addToTop(new PutOnBottomOfDeckAction(p, p, 1, false));
		} else if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			AbstractDungeon.actionManager.addToTop(new DiscardAction(p, p, 1, false));
		} else {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		}
	}

	@Override
	public void optionIce() {
		this.setOrbTexture("img/cards/small/orb-ice.png", "img/cards/portrait/orb-ice.png");
		this.rawDescription = "Gain 8 block. Shuffle a card into your draw pile.";
		this.initializeDescription();
	}

	@Override
	public void optionFire() {
		this.setOrbTexture("img/cards/small/orb-fire.png", "img/cards/portrait/orb-fire.png");
		this.rawDescription = "Gain 8 block. Discard a card.";
		this.initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.setOrbTexture("img/cards/small/orb.png", "img/cards/portrait/orb.png");
		this.rawDescription = "Gain 8 block. Gain 5 more block.";
		this.initializeDescription();
	}

}
