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
    public static Builder builder;
    public static WorldSaver saver;
    private Timer timer;
    public static List<Item> items = new ArrayList<Item>();
    public static InventoryPane inventoryPane = new InventoryPane();
    public static boolean showInfo = false;

    public PlayState(GameStateManager gsm) {
	super(gsm);
	world = Game.world;
	player = new Player(64, 64, 64, 64, 5);
	camera = new Camera(player);
	saver = new WorldSaver(world);
	timer = new Timer(1000, this);
	timer.start();
    }

    @Override
    public void update() {
	world.update();
	player.update();
	if (builder != null)
	    builder.update();
	items.forEach(Item::update);
	inventoryPane.update();
    }

    @Override
    public void render(Graphics2D g) {
	g.clearRect(0, 0, Game.width * Game.blocksize, Game.height * Game.blocksize);
	world.render(g);
	player.render(g);
	g.setColor(Color.white);
	g.drawString("Buildmode: " + buildMode, 10, 10);
	if (builder != null)
	    builder.render(g);
	items.forEach(item -> item.render(g));
	inventoryPane.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e, int k) {
	player.keyPressed(e, k);
	if (e.getKeyCode() == KeyEvent.VK_B) {
	    buildMode = !buildMode;
	    if (buildMode)
		builder = new Builder();
	    else
		builder = null;
	}
	Game.world.keyPressed(e, k);
    }

    @Override
    public void keyReleased(KeyEvent e, int k) {
	player.keyReleased(e, k);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (builder != null)
	    builder.mousePressed(e);
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
	if (builder != null) {
	    builder.mouseWheelMoved(e);
	}
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
