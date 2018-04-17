package blackmage.effects;

import com.badlogic.gdx.graphics.Color;

public class ColorFadeEffect {
	
	private Color color1;
	private Color color2;
	private Color outputColor;
	
	private int countDir = 1;
	
	public ColorFadeEffect(Color firstColor, Color secondColor,	Color startColor) {
		color1 = firstColor.cpy();
		color2 = secondColor.cpy();
		outputColor = startColor.cpy();
	}
	
	public void update() {
		float r = outputColor.r;
		float g = outputColor.g;
		float b = outputColor.b;
		
		float[] hsbColor = java.awt.Color.RGBtoHSB((int)(r * 255f), (int)(g * 255f), (int)(b * 255f), null);
		float angle = Math.round(hsbColor[0] * 360f);
	
		if(angle == Math.round(this.get360AngleFromColor(color1)) || angle == Math.round(this.get360AngleFromColor(color2))) {
			countDir *= -1;
		}
		
		angle += (float)countDir;
	
		hsbColor[0] = angle / 360f;
		java.awt.Color newColor = java.awt.Color.getHSBColor(hsbColor[0], 1f, 1f);
		
		outputColor.r = newColor.getRed() / 255f;
		outputColor.g = newColor.getGreen() / 255f;
		outputColor.b = newColor.getBlue() / 255f;
	}
	
	public Color getColor() {
		update();
		return outputColor.cpy();
	}
	
	private float get360AngleFromColor(Color c) {
		float r = c.r;
		float g = c.g;
		float b = c.b;
		
		float[] hsbColor = java.awt.Color.RGBtoHSB((int)(r * 255f), (int)(g * 255f), (int)(b * 255f), null);
		
		float angle = hsbColor[0] * 360f;
		
		return angle;
	}
}
