package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.IcePower;

public class SnowWall extends AbstractCustomCardWithType {
	public static final String ID = "SnowWall";
	private static final String NAME = "Snow Wall";
	private static final String IMG = "img/cards/icons/snowwall.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[1];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[1];
	private static final String DESCRIPTION = "Gain !B! block. NL Apply Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 1;
	private static final int BLOCK = 5;
	private int blockFromBuff = 0;
	
	public SnowWall() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);

		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new SnowWall();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeBlock(3);
		}
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		
		if(AbstractDungeon.player.hasPower("bm_ice_power")) {
			this.block += AbstractDungeon.player.getPower("bm_ice_power").amount;
			this.blockFromBuff = AbstractDungeon.player.getPower("bm_ice_power").amount;
			this.isBlockModified = true;
		}
	}


	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block + blockFromBuff));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 1), 1));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new FlameWall();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}
	
	
}
