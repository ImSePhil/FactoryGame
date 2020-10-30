package framework;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class PlayState extends State implements ActionListener {
	public static Point mousePosition;
	public static Camera camera;
	private World world;
	public static Player player;
	public static boolean buildMode;
	public static boolean farmMode;
	public static Interactor interactor = new Interactor();
	public static WorldSaver saver;
	private Timer timer;
	public static List<Item> items = new ArrayList<Item>();
	public List<Enemy> enemies = new ArrayList<Enemy>();
	public static InventoryPane inventoryPane = new InventoryPane();
	public static boolean showInfo = false;
	private Time time = new Time();
	private long oldTime = System.nanoTime();
	private long difference;
	private long fps;
	public static GUI gui;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		world = Game.world;
		player = new Player(64, 64, 64, 64, 3);
		camera = new Camera(player);
		saver = new WorldSaver(world);
		gui = new GUI();
		enemies.add(new Zombie(50, 50, 64, 64, 2));
		timer = new Timer(1000, this);
		timer.start();
	
	}

	@Override
	public void update() {
		world.update();
		player.update();
		enemies.forEach(Enemy::update);
		interactor.update();
		items.forEach(Item::update);
		inventoryPane.update();
		time.update();
		updateFrames();
		gui.update();
		
	}

	private void updateFrames() {
		difference = System.nanoTime() - oldTime;
		oldTime = System.nanoTime();
		fps = 1000000000 / difference;
	}

	@Override
	public void render(Graphics2D g) {
		g.clearRect(0, 0, Game.width * Game.blocksize, Game.height * Game.blocksize);
		world.render(g);
		player.render(g);
		enemies.forEach(e -> e.render(g));
		g.setColor(Color.white);
		g.drawString("Buildmode: " + buildMode, 10, 10);
		interactor.render(g);
		items.forEach(item -> item.render(g));
		time.render(g);
		g.drawString(fps + " ", 40, 100);
		inventoryPane.render(g);
		gui.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e, int k) {
		player.keyPressed(e, k);
		Game.world.keyPressed(e, k);
		gui.keyPressed(e,k);
		interactor.keyPressed(e,k);
	}

	@Override
	public void keyReleased(KeyEvent e, int k) {
		player.keyReleased(e, k);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		interactor.mousePressed(e);
		inventoryPane.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e, Point mousePosition) {
		this.mousePosition = mousePosition;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			saver.save();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		interactor.mouseWheelMoved(e);
	}

	public static boolean isSlotFree(int x, int y) {
		for (Item item : items) {
			if (item.x == x) {
				if (item.y == y)
					return false;
			}
		}
		return true;
	}

}
