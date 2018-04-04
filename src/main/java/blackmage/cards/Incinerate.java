package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;

public class Incinerate extends AbstractCustomCardWithType {
	
	private static final String ID = "Incinerate";
	private static final String NAME = "Incinerate";
	private static final String IMG = "img/cards/icons/incinerate.png";
	private static final String DESCRIPTION = "Deal !D! Fire damage to all enemies for each stack of Fire. NL Exhaust.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int MAGIC = 0;

	public Incinerate() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = MAGIC;
		this.magicNumber = MAGIC;
		this.isMultiDamage = true;
		this.exhaust = true;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new SheerCold();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Incinerate();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(0);
		}
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		
		if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			this.magicNumber = AbstractDungeon.player.getPower("bm_fire_power").amount;
			this.isMagicNumberModified = true;
			this.rawDescription = "Deal !D! Fire damage to all enemies !M! times. NL Exhaust.";
			this.initializeDescription();
		}
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		super.calculateCardDamage(arg0);
		
		if(AbstractDungeon.player.hasPower("bm_fire_power")) {
			this.magicNumber = AbstractDungeon.player.getPower("bm_fire_power").amount;
			this.isMagicNumberModified = true;
			this.rawDescription = "Deal !D! Fire damage to all enemies !M! times. NL Exhaust.";
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.FIRE));
		}
	}

}
