package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.FirePower;

public class BlackFire extends AbstractCustomCardWithType {
	
	public static final String ID = "BlackFire";
	private static final String NAME = "Black Fire";
	private static final String IMG = "img/cards/icons/strike-fire.png";
	private static final String DESCRIPTION = "Deal !D! Dark Fire damage. Gain 1 Fire.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.FIRE;

	private static final int COST = 1;
	private static final int ATK_DMG = 9;

	public BlackFire() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = ATK_DMG;
		this.setBackgroundTexture(BlackMageMod.ATTACK_BG[3], BlackMageMod.ATTACK_BG_P[3]);
	}
	
	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new CursedIce();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new BlackFire();	
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
		AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, p, this.damage, AbstractGameAction.AttackEffect.SLASH_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FirePower(p, 1), 1));
	}

}
