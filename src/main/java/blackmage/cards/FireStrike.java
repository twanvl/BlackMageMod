package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCardWithRender;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class FireStrike extends AbstractCustomCardWithType {

	public static final String ID = "strike_fire";
	private static final String NAME = "Fire Strike";
	private static final String IMG = "img/cards/icons/strike-fire.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[2];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[2];
	private static final String DESCRIPTION = "Deal !D! fire damage to all enemies. NL Apply Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;
	
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	
	public FireStrike() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 0, COLOR_TYPE);
		
		this.baseDamage = ATTACK_DMG;
		this.damageType = EnumPatch.FIRE_DAMAGE;
		this.damageTypeForTurn = this.damageType;
	}

	@Override
	public AbstractCard makeCopy() {
		return new FireStrike();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
		System.out.println(this.damageType.toString());
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new IceStrike();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}
}