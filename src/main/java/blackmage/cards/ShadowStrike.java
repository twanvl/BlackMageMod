package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;

public class ShadowStrike extends AbstractCustomCardWithType {
	
	public static final String ID = "ShadowStrike";
	private static final String NAME = "Shadow Strike";
	private static final String IMG = "img/cards/icons/shadowstrike.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[3];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[3];
	private static final String DESCRIPTION = "Deal !D! dark damage.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.DARK;

	private static final int COST = 1;
	private static final int ATK_DMG = 7;
	
	public ShadowStrike() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		this.baseDamage = ATK_DMG;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ShadowStrike();
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
		AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, p, this.damage, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		
		return null;
	}
}
