package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCardWithRender;
import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.FreezePower;

public class SheerCold extends CustomCardWithRender {
	public static final String ID = "SheerCold";
	private static final String NAME = "Sheer Cold";
	private static final String IMG = "img/cards/icons/sheercold.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[1];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[1];
	private static final String DESCRIPTION = "Freeze all enemies for 1 turn.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;

	private static final int COST = 3;
	
	public SheerCold() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1);
	}

	@Override
	public AbstractCard makeCopy() {
		return new SheerCold();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeBaseCost(2);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mon, p, new FreezePower(mon), 1));
		}
	}
}
