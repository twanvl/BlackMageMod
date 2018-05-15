package blackmage.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class ColorFadeEffect {
	
	private Color color1;
	private Color color2;
	private Color outputColor;
	private float absoluteAngle = -100f;
	private float speed;
	
	private int countDir = 1;
	
	public ColorFadeEffect(Color firstColor, Color secondColor,	Color startColor) {
		this(firstColor, secondColor, startColor, 0.5f);
	}
	
	public ColorFadeEffect(Color firstColor, Color secondColor, Color startColor, float speed) {
		color1 = firstColor.cpy();
		color2 = secondColor.cpy();
		outputColor = startColor.cpy();
		this.speed = speed;
	}
	
	public void update() {
		float r = outputColor.r;
		float g = outputColor.g;
		float b = outputColor.b;
		
		float[] hsbColor = java.awt.Color.RGBtoHSB((int)(r * 255f), (int)(g * 255f), (int)(b * 255f), null);
		
		if(absoluteAngle == -100f) absoluteAngle = hsbColor[0];
		
		absoluteAngle += (((float) countDir * speed) * (Gdx.graphics.getDeltaTime() * 60)) / 360.0f;
		
		if(absoluteAngle > 1.0f) {
			absoluteAngle -= 1.0f;
		} else if(absoluteAngle < 0.0f) {
			absoluteAngle += 1.0f;
		}
		
		float angle = absoluteAngle * 360f;
		
		//System.out.println(angle + "|" + Math.round(angle) + "|" + Math.round(this.get360AngleFromColor(color1)) + "|" + Math.round(this.get360AngleFromColor(color2)));
		
		if(Math.round(angle) == Math.round(this.get360AngleFromColor(color1)) || Math.round(angle) == Math.round(this.get360AngleFromColor(color2))) {
			countDir *= -1;
			angle += countDir;
			absoluteAngle = angle / 360.0f;
		}
		
		hsbColor[0] = angle / 360f;
		java.awt.Color newColor = java.awt.Color.getHSBColor(hsbColor[0], 1f, 1f);
		
		outputColor.r = newColor.getRed() / 255f;
		outputColor.g = newColor.getGreen() / 255f;
		outputColor.b = newColor.getBlue() / 255f;
	}
	
	public Color getColor(boolean doUpdate) {
		if(doUpdate)
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
