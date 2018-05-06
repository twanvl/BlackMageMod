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
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class Ash extends AbstractCustomCardWithType {
	
	public static final String ID = "bm_Ash";
	private static final String NAME = "Ashes";
	private static final String IMG = "img/cards/icons/ash.png";
	private static final String DESCRIPTION = "Deal !D! fire damage. NL Gain 1 Fire. NL Exhaust.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

	private static final int COST = 0;
	private static final int ATK_DMG = 3;
	
	public Ash() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, AbstractCustomCardWithType.CardColorType.FIRE);
		
		this.baseDamage = ATK_DMG;
		this.exhaust = true;
		this.setBackgroundTexture(BlackMageMod.ATTACK_BG[0], BlackMageMod.ATTACK_BG_P[0]);
	}

	@Override
	public AbstractCard makeCopy() {
		return new Ash();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.upgradeName();
			this.upgradeDamage(2);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}

}
