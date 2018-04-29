package blackmage.patches;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;

import blackmage.BlackMageMod;

@SpirePatch(cls="com.megacrit.cardcrawl.core.CardCrawlGame", method="update")
public class CardCrawlGamePatch {
	
	@SpireInsertPatch(
			rloc=1,
			localvars={}
			)
	public static void Insert(Object meObj) {
		BlackMageMod.updateColor();
	}
}
