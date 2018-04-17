package blackmage.particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.random.Random;

import blackmage.BlackMageMod;

public class Particle {

	private final int maxLifeSpan;
	private int lifeSpan;
	private Vector2 pos;
	private Vector2 velocity;
	private Vector2 acceleration = new Vector2(0f, 0f);
	private Texture texture = BlackMageMod.getTexture("img/particles/particle.png");
	private Color color;

	public Particle(Vector2 pos, Vector2 vel, int lifeSpan, Color particleColor) {
		this.pos = pos;
		this.velocity = vel;
		this.maxLifeSpan = lifeSpan;
		this.lifeSpan = new Random().random(lifeSpan / 2, lifeSpan);
		this.color = new Color(particleColor);
	}

	public void update() {
		this.velocity.add(acceleration);
		this.pos.add(velocity);
		lifeSpan--;
	}

	public void render(SpriteBatch sb) {
		float scale = 4.0f * (1.0f * ((float) lifeSpan / (float) maxLifeSpan)) * Settings.scale;
		float x = pos.x * Settings.scale;
		float y = pos.y * Settings.scale;

		sb.setColor(color.r, color.g, color.b, 1);
		sb.draw(texture, x, y, 0, 0, 5f, 5f, scale, scale, 0.0f, 0, 0, 5, 5, false, false);
	}

	public void setAcc(Vector2 newAcc) {
		this.acceleration = newAcc;
	}

	public boolean isDead() {
		return lifeSpan <= 0;
	}
}
