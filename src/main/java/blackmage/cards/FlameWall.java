package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCardWithRender;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class FlameWall extends AbstractCustomCardWithType {
	public static final String ID = "FlameWall";
	private static final String NAME = "Flame Wall";
	private static final String IMG = "img/cards/icons/flamewall.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[2];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[2];
	private static final String DESCRIPTION = "Gain !D! block. NL Apply Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;

	private static final int COST = 1;
	private static final int BLOCK = 5;
	
	public FlameWall() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = BLOCK;
		this.damageType = EnumPatch.FIRE_DAMAGE;
		this.damageTypeForTurn = EnumPatch.FIRE_DAMAGE;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FlameWall();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.damage));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
	}

	@Override
	public AbstractCustomCardWithType getOpposite() {
		return new SnowWall();
	}
}
