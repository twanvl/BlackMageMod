package blackmage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.WeakPower", method="atDamageGive")
public class WeakPatch {
	public static float Replace(Object obj_instance, float damage, DamageType type) {
		WeakPower power = (WeakPower)obj_instance;
		if (type != DamageInfo.DamageType.THORNS && type != DamageInfo.DamageType.HP_LOSS) {
			if ((!power.owner.isPlayer) && (AbstractDungeon.player.hasRelic("Paper Crane"))) {
				return damage * 0.5F;
				}
			return damage * 0.75F;
			}
		return damage;
	}
}
