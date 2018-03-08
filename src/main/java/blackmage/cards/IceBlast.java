package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.IcePower;

public class IceBlast extends AbstractCustomCardWithType {

	public static final String ID = "IceBlast";
	private static final String NAME = "Ice Blast";
	private static final String IMG = "img/cards/icons/strike-ice.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[1];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[1];
	private static final String DESCRIPTION = "Deal !D! ice damage. NL Draw a card. NL Apply 2 Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	
	public IceBlast() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 0, COLOR_TYPE);
		
		this.damageType = EnumPatch.ICE_DAMAGE;
		this.damageTypeForTurn = EnumPatch.ICE_DAMAGE;
		
		this.baseDamage = ATTACK_DMG;
	}
	
	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new FireBlast();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new IceBlast();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(4);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster mon) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(mon, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 2), 1));
	}

}
