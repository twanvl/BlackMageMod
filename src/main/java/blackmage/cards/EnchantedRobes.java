package blackmage.cards;

import java.util.ArrayList;
import java.util.Random;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;
import basemod.helpers.ModalChoice.Callback;
import basemod.helpers.ModalChoiceBuilder;
import blackmage.choicecards.*;
import blackmage.patches.EnumPatch;

public class EnchantedRobes extends CustomCard implements Callback{
	
	public static final String ID = "EnchantedRobes";
	private static final String NAME = "Enchanted Robes";
	private static final String IMG = "img/cards/icons/robes.png";
	
	private static final String DESCRIPTION = "Pick 1 of three random robe enchantments. NL Innate.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	
	private static final int COST = 1;
	private static final int POOL = 1;
	
	public EnchantedRobes() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, POOL);
		this.isInnate = true;
	}

	@Override
	public AbstractCard makeCopy() {
		return new EnchantedRobes();
	}

	@Override
	public void upgrade() {
		
	}

	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {
		
		ArrayList<AbstractCard> choices = new ArrayList<AbstractCard>();
		
		choices.add(new EnergizeChoice()); //Energized
		choices.add(new MetallicizeChoice()); //Shielded
		choices.add(new StrengthChoice()); //Empowering
		choices.add(new CardDrawChoice()); //Pocketed
		choices.add(new LuckyChoice()); //Lucky
		//Spiked
		
		ModalChoiceBuilder m = new ModalChoiceBuilder();
		m.setCallback(this);
		m.setTitle("Pick an Enchantment");
		int iter = 0;
		for(int i = choices.size() - 1; i >= 0; i--) {
			Random rand = new Random();
			AbstractCard choice = choices.get(rand.nextInt(choices.size()));
			m.addOption(choice);
			choices.remove(choice);
			iter++;
			if(iter > 2)
				break;
		}
		
		//Invigorate
		//Increased Card Draw
		//Metallicize
		//Strength
		//Dexterity
		//Burst effect for this class
		
		//pick 3 at random
		
		
		
		ModalChoice choice = m.create();
		choice.open();
	}

	@Override
	public void optionSelected(int optionNum) {
		//NOP
	}
}
