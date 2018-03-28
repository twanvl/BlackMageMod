package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.actions.DiscardToHandAction;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class FireBlast extends AbstractCustomCardWithType {
	
	public static final String ID = "FireBlast";
	private static final String NAME = "Fire Blast";
	private static final String IMG = "img/cards/icons/strike-fire.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[2];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[2];
	private static final String DESCRIPTION = "Deal !D! fire damage. Pick 1 card from your discard pile and add it to your hand. NL Apply 2 Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;

	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	
	public FireBlast() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = ATTACK_DMG;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new IceBlast();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FireBlast();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(4);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(p));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 2), 2));
	}
}
