package blackmage.patches;

//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.GlyphLayout;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.Settings;
//
//import basemod.helpers.SuperclassFinder;

public class DescriptionEnergyPatch {

//	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.AbstractCard", method = "renderDescription")
//	public static class renderDescription{
//		@SpireInsertPatch(loc = 1792, localvars = {"gl", "tmp", "start_x", "spacing", "i"})
//		public static void Insert(AbstractCard card, SpriteBatch sb, GlyphLayout gl, String tmp, float start_x, float spacing, int i) {
//			if(tmp.equals("[BM] ")) {
//				
//				float card_energy_width = 24 * Settings.scale;
//				gl.width = card_energy_width * card.drawScale;
//				float tmp2 = (card.description.size() - 4) * spacing;
//				
//				float xPos = (start_x - card.current_x) / Settings.scale / card.drawScale;
//				float yPos = -tmp2 - 172.0F + card_energy_width * card.drawScale + i * spacing * 2.0F;
//				
//				try {
//					TextureAtlas orbTexture = new TextureAtlas(Gdx.files.internal("img/character/orb/orb.atlas"));
//					TextureAtlas.AtlasRegion orb_texture = orbTexture.findRegion("orb");
//					Method renderSmallEnergyMethod = SuperclassFinder.getSuperClassMethod(card.getClass(), "renderSmallEnergy", SpriteBatch.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
//					renderSmallEnergyMethod.setAccessible(true);
//					
//					renderSmallEnergyMethod.invoke(card, sb, orb_texture,xPos, yPos);
//				
//				} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				start_x += gl.width;
//			}
//		}
//	}
}
