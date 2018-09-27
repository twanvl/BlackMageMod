package blackmage.patches;

import blackmage.character.player.BlackMageCharacter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.scenes.AbstractScene;

import basemod.helpers.SuperclassFinder;

@SpirePatch(clz=com.megacrit.cardcrawl.scenes.AbstractScene.class, method=SpirePatch.CONSTRUCTOR)
public class BottomScenePatch {
	
	@SpireInsertPatch(
			rloc=2,
			localvars= {}
		)
	public static void Insert(AbstractScene meObj, String atlasUrl) {
		if(AbstractDungeon.player instanceof BlackMageCharacter && atlasUrl.equals("bottomScene/scene.atlas"))
			try {
				Field atlasField = SuperclassFinder.getSuperclassField(meObj.getClass(), "atlas");
				setFinalStatic(meObj, atlasField, new TextureAtlas(Gdx.files.internal("img/scenes/bottom/scene.atlas")));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("COULD NOT SET NEW ATLAS");
				e.printStackTrace();
			}
	}
	
	public static void setFinalStatic(Object parent, Field field, Object newValue) throws Exception{
		field.setAccessible(true);

	    Field modifiersField = Field.class.getDeclaredField("modifiers");
	    modifiersField.setAccessible(true);
	    modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

	    field.set(parent, newValue);
	}
}
