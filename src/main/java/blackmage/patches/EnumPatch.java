package blackmage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class EnumPatch {
	@SpireEnum
	public static AbstractCard.CardColor BLACK_MAGE;
	
	@SpireEnum
	public static AbstractPlayer.PlayerClass BLACK_MAGE_CLASS;
	
	@SpireEnum
	public static DamageInfo.DamageType ICE_DAMAGE;
	
	@SpireEnum
	public static DamageInfo.DamageType FIRE_DAMAGE;
}
