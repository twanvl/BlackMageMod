package blackmage.patches;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import blackmage.BlackMageMod;

@SpirePatch(clz=com.megacrit.cardcrawl.core.CardCrawlGame.class, method="update")
public class CardCrawlGamePatch {
	
	@SpireInsertPatch(
			rloc=1,
			localvars={}
			)
	public static void Insert(Object meObj) {
		BlackMageMod.updateColor();
	}
}
