package blackmage.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.VulnerablePower", method="atDamageReceive")
public class VulnerablePatch {
	public static float Replace(Object obj_instance, float damage, DamageType type) {
		
		VulnerablePower power = (VulnerablePower) obj_instance;
		
		if(type != DamageInfo.DamageType.THORNS && type != DamageInfo.DamageType.HP_LOSS) {
			if ((power.owner.isPlayer) && (AbstractDungeon.player.hasRelic("Odd Mushroom"))) {
				return damage * 1.25F;
			}
			
			if ((power.owner != null) && (!power.owner.isPlayer) && (AbstractDungeon.player.hasRelic("Paper Frog"))) {
				return damage * 1.75F;
			}
			return damage * 1.5F;
		}
		return damage;
	}
}
