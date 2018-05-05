package blackmage.cards;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.patches.EnumPatch;

public class Rend extends AbstractCustomCardWithType {
	
	public static final String ID = "Rend";
	private static final String NAME = "Rend";
	private static final String IMG = "img/cards/icons/badbook.png";
	private static final String DESCRIPTION = "Deal !D! Damage. Increase damage by !M! for each curse in your deck.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = CardColorType.DARK;
	
	private static final int COST = 2;
	private static final int MAGIC = 3;
	private static final int DAMAGE = 12;

	public Rend() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = DAMAGE;
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		return null;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Rend();
	}

	@Override
	public void upgrade() {
		if(!upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(3);
		}
	}
	
	

	@Override
	public void applyPowers() {
		super.applyPowers();
		
		int damageIncrease = this.increaseDamageForCurses();
		
		if(damageIncrease > 0) {
			this.isDamageModified = true;
		}
		
		this.damage += damageIncrease;
		
		this.rawDescription = "Deal !D! Damage. Damage is increased by " + damageIncrease + ".";
	}

	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		super.calculateCardDamage(arg0);

		int damageIncrease = this.increaseDamageForCurses();
		
		if(damageIncrease > 0) {
			this.isDamageModified = true;
		}
		
		this.damage += damageIncrease;
		
		this.rawDescription = "Deal !D! Damage. Damage is increased by " + damageIncrease + ".";
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage), AttackEffect.BLUNT_HEAVY));
	}
	
	private int increaseDamageForCurses() {
		int amount = 0;
		
		ArrayList<AbstractCard> draw = AbstractDungeon.player.drawPile.group;
		ArrayList<AbstractCard> hand = AbstractDungeon.player.hand.group;
		ArrayList<AbstractCard> discard = AbstractDungeon.player.discardPile.group;
		
		ArrayList<ArrayList<AbstractCard>> lists = new ArrayList<ArrayList<AbstractCard>>();
		
		lists.add(draw);
		lists.add(hand);
		lists.add(discard);
		
		for(ArrayList<AbstractCard> group : lists) {
			for(AbstractCard card : group) {
				if(card.type == CardType.CURSE) {
					amount += magicNumber;
				}
			}
		}
		return amount;
	}

}
