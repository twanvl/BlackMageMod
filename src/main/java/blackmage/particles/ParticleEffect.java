package blackmage.particles;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ParticleEffect {

	private Rectangle spawnRegion;
	private float spawnRate;
	private int spawnAmount;
	private int particleLifeSpan;
	private Color particleColor;
	private Vector2 particleAcc;
	private ArrayList<Particle> particles = new ArrayList<Particle>();

	public ParticleEffect(Rectangle spawnRegion, float spawnRate, int spawnAmount, int particleLifeSpan,
			Vector2 particleAcc, Color particleColor) {
		this.spawnRegion = spawnRegion;
		this.spawnRate = spawnRate;
		this.particleColor = particleColor;
		this.spawnAmount = spawnAmount;
		this.particleAcc = particleAcc;
		this.particleLifeSpan = particleLifeSpan;
	}

	public void update() {
		Random rand = new Random();
		if (rand.nextFloat() <= spawnRate) {
			for (int i = 0; i < spawnAmount; i++) {
				spawnParticle();
			}
		}

		for (int i = particles.size() - 1; i >= 0; i--) {
			particles.get(i).update();
			if (particles.get(i).isDead()) {
				particles.remove(i);
			}
		}
	}

	private void spawnParticle() {
		Random rand = new Random();

		float startX = spawnRegion.x + (rand.nextFloat() * spawnRegion.width);
		float startY = spawnRegion.y + (rand.nextFloat() * spawnRegion.height);

		Vector2 startPos = new Vector2(startX, startY);
		Vector2 startVel = new Vector2((rand.nextFloat() - 0.5f) * 2.0f, (rand.nextFloat() - 0.5f) * 2.0f);

		Particle particle = new Particle(startPos, startVel, particleLifeSpan, particleColor);

		particle.setAcc(this.particleAcc);

		particles.add(particle);
	}

	public void render(SpriteBatch sb) {
		for (Particle p : particles) {
			p.render(sb);
		}
		sb.setColor(1, 1, 1, 1);
	}

	public void moveSpawnRegion(int newX, int newY) {
		this.spawnRegion.x = newX;
		this.spawnRegion.y = newY;
	}

	public float getSpawnRate() {
		return spawnRate;
	}

	public void setSpawnRate(float spawnRate) {
		this.spawnRate = spawnRate;
	}

	public int getSpawnAmount() {
		return spawnAmount;
	}

	public void setSpawnAmount(int spawnAmount) {
		this.spawnAmount = spawnAmount;
	}

	public int getParticleLifeSpan() {
		return particleLifeSpan;
	}

	public void setParticleLifeSpan(int particleLifeSpan) {
		this.particleLifeSpan = particleLifeSpan;
	}

	public Color getParticleColor() {
		return particleColor;
	}

	public void setParticleColor(Color particleColor) {
		this.particleColor = particleColor;
	}

	public Vector2 getParticleAcc() {
		return particleAcc;
	}

	public void setParticleAcc(Vector2 particleAcc) {
		this.particleAcc = particleAcc;
	}

}
