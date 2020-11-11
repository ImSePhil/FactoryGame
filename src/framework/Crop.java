package framework;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class Crop extends GameObject implements ActionListener {
	ArrayList<BufferedImage> stage = new ArrayList<BufferedImage>();
	int progress;
	Timer timer;
	boolean finished = false;

	public Crop(float x, float y, int width, int height, Plant crop) {
		super(x, y, width, height);
		Random r = new Random();
		int time = r.nextInt(20000) - 10000;
		timer = new Timer(40000 + time, this);
		stage.add(crop.stage0.getSprite());
		stage.add(crop.stage1.getSprite());
		stage.add(crop.stage2.getSprite());
		stage.add(crop.stage3.getSprite());
		stage.add(crop.stage4.getSprite());
		stage.add(crop.stage5.getSprite());
		stage.add(crop.stage6.getSprite());
		stage.add(crop.stage7.getSprite());
		progress = 0;
		timer.start();
	}

	public void render(Graphics2D g) {
		g.drawImage(stage.get(progress), (int) x, (int) y, width, height, null);
	}

	public void update() {

	}

	@Override
	protected float getX() {
		return 0;
	}

	@Override
	protected float getY() {
		return 0;
	}

	@Override
	protected int getWidth() {
		return 0;
	}

	@Override
	protected int getHeight() {
		return 0;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Random r = new Random();
		int time = r.nextInt(20000) - 10000;
		timer.setDelay(40000 + time);
		if (progress < 7) {
			progress++;
		} else {
			timer.stop();
			finished = true;
		}
	}
}
