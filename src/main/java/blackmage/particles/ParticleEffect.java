package blackmage.particles;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ParticleEffect {

	private Rectangle spawnRegion;
	private float spawnRate;
	private int spawnAmount;
	private Vector2 initAcc;
	private Particle baseParticle;
	
	private ArrayList<Particle> particles = new ArrayList<Particle>();

	
	public ParticleEffect(Rectangle spawnRegion, float spawnRate, int spawnAmount, Vector2 particleAcc, Particle baseParticle) {
		this.spawnRegion = spawnRegion;
		this.spawnRate =  spawnRate;
		this.spawnAmount = spawnAmount;
		this.initAcc = particleAcc;
		this.baseParticle = baseParticle;
		
	}

	public void update() {
		Random rand = new Random();
		if (rand.nextFloat() <= (spawnRate * (Gdx.graphics.getDeltaTime() * 60))) {
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
		
		Particle particle = baseParticle.getCopy(startPos, startVel);

		particle.setAcc(this.initAcc);

		particles.add(particle);
	}

	public void render(SpriteBatch sb) {
		for (Particle p : particles) {
			p.render(sb);
		}
		sb.setColor(1, 1, 1, 1);
	}

	public void moveSpawnRegion(float f, float g) {
		this.spawnRegion.x = f;
		this.spawnRegion.y = g;
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
	
	public void setParticleColor(Color c) {
		this.baseParticle.setColor(c);
	}
	
	public void setParticleTexture(Texture t) {
		this.baseParticle.setTexture(t);
	}
}
