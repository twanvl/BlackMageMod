package blackmage.patches;

import java.lang.reflect.Field;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

import basemod.ReflectionHacks;
import basemod.helpers.SuperclassFinder;
import blackmage.cards.AbstractCustomCardWithType;

@SpirePatch(cls="com.megacrit.cardcrawl.screens.select.GridCardSelectScreen", method="update")
public class GridSelectScreenPatch {
	
	public static boolean renderAsExchange = false;
	
	@SpireInsertPatch(
		loc=116,
		localvars= {}
	)
	public static void Insert(GridCardSelectScreen meObj) {
		Field previewCardField;
		try {
			previewCardField = SuperclassFinder.getSuperclassField(meObj.getClass(), "upgradePreviewCard");
			previewCardField.setAccessible(true);
			AbstractCard previewCard = (AbstractCard)previewCardField.get(meObj);
			AbstractCard hoveredCard = (AbstractCard)ReflectionHacks.getPrivate(meObj, meObj.getClass(), "hoveredCard");
			
			if(hoveredCard instanceof AbstractCustomCardWithType) {
				System.out.println("instanceof");
				AbstractCustomCardWithType accwtCard = (AbstractCustomCardWithType)hoveredCard;

				if(renderAsExchange) {
					System.out.println("renderAsExchange");
					previewCard = accwtCard.getOpposite(accwtCard.upgraded);
				}
			}
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	@SpirePatch(cls="com.megacrit.cardcrawl.screens.select.GridCardSelectScreen", method="render")
	public static class SelectRenderPatch{
		@SpireInsertPatch(
				loc=567,
				localvars= {}
			)
		public static void Insert(GridCardSelectScreen meObj, SpriteBatch sb) {
			Field previewCardField;
			try {
				previewCardField = SuperclassFinder.getSuperclassField(meObj.getClass(), "upgradePreviewCard");
				previewCardField.setAccessible(true);
				AbstractCard previewCard = (AbstractCard)previewCardField.get(meObj);
				System.out.println(previewCard.name);
				AbstractCard hoveredCard = (AbstractCard)ReflectionHacks.getPrivate(meObj, meObj.getClass(), "hoveredCard");
				
				if(hoveredCard instanceof AbstractCustomCardWithType) {
					System.out.println("instanceof");
					AbstractCustomCardWithType accwtCard = (AbstractCustomCardWithType)hoveredCard;

					if(renderAsExchange) {
						System.out.println("renderAsExchange");
						previewCard = accwtCard.getOpposite(accwtCard.upgraded);
						System.out.println(previewCard.name);
					}
				}
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				
				e.printStackTrace();
			}
		}
	}
}
