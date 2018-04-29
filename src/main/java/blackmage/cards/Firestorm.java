package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class Firestorm extends AbstractCustomCardWithType {
	public static final String ID = "Firestorm";
	private static final String NAME = "Firestorm";
	private static final String IMG = "img/cards/icons/firestorm.png";
	private static final String DESCRIPTION = "Deal !D! fire damage to all enemies. Apply !M! Vulnerable. NL Gain 1 Fire";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;

	private static final int COST = 1;
	private static final int ATK_DMG = 5;
	private static final int MAGIC = 1;
	
	public Firestorm() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseMagicNumber = MAGIC;
		this.magicNumber = this.baseMagicNumber;
		
		this.baseDamage = ATK_DMG;
		this.isMultiDamage = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Firestorm();
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
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.SLASH_DIAGONAL));
		
		for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, p, new VulnerablePower(mon, this.magicNumber, false), this.magicNumber));
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new SnowStorm();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}
}
