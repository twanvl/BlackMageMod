package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomPlayer;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class TemperatureShock extends CustomCard {
	public static final String ID = "TemperatureShock";
	private static final String NAME = "Temperature Shock";
	private static final String IMG = "img/cards/icons/tempshock.png";
	private static final String DESCRIPTION = "Deal !D! damage. If you have Fire or Ice, NL gain [R]. NL Remove Fire and Ice.";
	private static final String UPGRADE_DESC = "Deal !D! damage. If you have Fire or Ice, NL gain [R] [R]. NL Remove Fire and Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private static final int COST = 1;
	private static final int MAGIC = 1;
	private static final int ATK_DMG = 8;
	
	public TemperatureShock() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
		
		this.baseDamage = ATK_DMG;
		this.baseMagicNumber = MAGIC;
		this.magicNumber = this.baseMagicNumber;
	}

	@Override
	public AbstractCard makeCopy() {
		return new TemperatureShock();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.upgradeDamage(4);
			this.rawDescription = UPGRADE_DESC;
			this.initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
		
		if(p.hasPower("bm_ice_power") || p.hasPower("bm_fire_power"))
			AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
		
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_ice_power"));
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "bm_fire_power"));
		
		BlackMageMod.resetOrbColor((CustomPlayer)AbstractDungeon.player);
	}
}
