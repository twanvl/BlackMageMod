package blackmage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.CurlUp;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.CurlUp", method="onAttacked")
public class CurlUpPatch {
	public static int Replace(Object obj_instance, DamageInfo info, int damageAmount) {
		CurlUp power = (CurlUp)obj_instance;
		
		if ((damageAmount < power.owner.currentHealth) && (damageAmount > 0) && (info.owner != null) && (info.type != DamageType.THORNS)) {
			 AbstractDungeon.actionManager.addToBottom(new ChangeStateAction((AbstractMonster)power.owner, "CLOSED"));
			 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(power.owner, power.owner, power.amount));
			 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(power.owner, power.owner, "Curl Up"));
		}
		return damageAmount;
	
	}
}
