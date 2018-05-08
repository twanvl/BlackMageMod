package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;

public class ShadowWall extends AbstractCustomCardWithType{
	public static final String ID = "ShadowBarrier";
	private static final String NAME = "Shadow Barrier";
	private static final String IMG = "img/cards/icons/shadowwall.png";
	private static final String DESCRIPTION = "Deal !D! dark damage. Gain !B! block.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.DARK;
	
	private static final int COST = 2;
	private static final int ATK_DMG = 10;
	private static final int BLOCK = 10;

	public ShadowWall() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, COLOR_TYPE);
		
		this.baseDamage = ATK_DMG;
		this.baseBlock = BLOCK;
	}

	@Override
	public AbstractCard makeCopy() {
		return new ShadowWall();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(4);
			this.upgradeBlock(5);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new LoseHPAction(m, p, this.damage, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}
}
