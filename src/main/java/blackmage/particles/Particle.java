package blackmage.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.random.Random;

public class Particle {

	private final int maxLifeSpan;
	private int lifeSpan;
	private Vector2 pos;
	private Vector2 velocity;
	private Vector2 acceleration = new Vector2(0f, 0f);
	private Texture texture;
	private Color color;
	private float rotation;

	public Particle(Vector2 pos, Vector2 vel, int lifeSpan, Color particleColor, Texture texture) {
		this.pos = pos;
		this.velocity = vel;
		this.maxLifeSpan = lifeSpan;
		this.lifeSpan = new Random().random(lifeSpan / 2, lifeSpan);
		this.color = new Color(particleColor);
		this.texture = texture;
		
	}
	
	public Particle(int lifeSpan, Color particleColor, Texture texture) {
		this(new Vector2(0,0), new Vector2(0,0), lifeSpan, particleColor, texture);
	}
	
	public Particle getCopy(Vector2 pos, Vector2 vel) {
		return new Particle(pos, vel, maxLifeSpan, color, texture);
	}

	public void update() {
		this.velocity.add(acceleration);
		this.pos.add(velocity);
		lifeSpan--;
	}

	public void render(SpriteBatch sb) {
		float scale = 2.0f * (1.0f * ((float) lifeSpan / (float) maxLifeSpan)) * Settings.scale;
		float x = pos.x /* * Settings.scale*/;
		float y = pos.y /* * Settings.scale*/;
		
		Vector2 angleVect = new Vector2(velocity);
		angleVect.x *= -1;
		angleVect.y *= -1;
		angleVect.rotate(90.0f);
		
		rotation = angleVect.angle();

		sb.setColor(color.r, color.g, color.b, 1);
		sb.draw(texture, x, y, 15.0f / 2.0f, 15.0f / 2.0f, 15f, 15f, scale, scale, rotation, 0, 0, 15, 15, false, false);
	}

	public void setAcc(Vector2 newAcc) {
		this.acceleration = newAcc;
	}
	
	public int getLifeSpan() {
		return lifeSpan;
	}

	public Texture getTexture() {
		return texture;
	}

	public Color getColor() {
		return color;
	}

	public void setLifeSpan(int lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isDead() {
		return lifeSpan <= 0;
	}
}
