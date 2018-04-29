package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import blackmage.patches.EnumPatch;
import blackmage.powers.IcePower;

public class SnowStorm extends AbstractCustomCardWithType {
	public static final String ID = "SnowStorm";
	private static final String NAME = "Snow Storm";
	private static final String IMG = "img/cards/icons/blizzard.png";
	private static final String DESCRIPTION = "Deal !D! ice damage to all enemies 2 times. Apply !M! Weak. NL Gain 1 Ice";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 1;
	private static final int ATK_DMG = 3;
	private static final int MAGIC = 1;
	
	public SnowStorm() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseMagicNumber = MAGIC;
		this.magicNumber = this.baseMagicNumber;
		
		this.baseDamage = ATK_DMG;
		this.isMultiDamage = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new SnowStorm();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
			this.upgradeMagicNumber(1);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < 2; i++)
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.SLASH_DIAGONAL));
		
		for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, p, new WeakPower(mon, this.magicNumber, false), this.magicNumber));
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 1), 1));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new Firestorm();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}
}
