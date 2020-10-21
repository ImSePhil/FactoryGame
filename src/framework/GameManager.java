package framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameManager extends JPanel implements MouseWheelListener, ActionListener, KeyListener, ComponentListener,
	MouseMotionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    public static int width;
    public static int height;
    public static final int SCALE = 3;
    private Timer timer;
    private GameStateManager gsm;

    public GameManager() {
	super();
	setPreferredSize(new Dimension(1200, 800));
	addKeyListener(this);
	addComponentListener(this);
	addMouseMotionListener(this);
	addMouseListener(this);
	addMouseWheelListener(this);
	setFocusable(true);
	requestFocus();
	setBackground(Color.WHITE);
	width = getPreferredSize().width;
	height = getPreferredSize().height;
	gsm = new GameStateManager(GameStateManager.PLAYSTATE);
    }

    @Override
    public void addNotify() {
	super.addNotify();
	timer = new Timer(1000 / 60, this);
	timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setBackground(Color.BLACK);
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	gsm.render(g2);
    }

    private void update() {
	gsm.update();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
	gsm.keyPressed(e, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
	gsm.keyReleased(e, e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	update();
	repaint();
    }

    @Override
    public void componentResized(ComponentEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
	gsm.mouseMoved(e, this.getMousePosition());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
	gsm.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
	gsm.mouseWheelMoved(e);
    }
}
