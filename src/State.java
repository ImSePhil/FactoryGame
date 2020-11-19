package FactoryGame.Klassendiagramm;

public abstract class State {

	protected GameStateManager gsm;

	public State(GameStateManager gsm) {
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	public abstract void update();

	public abstract void render(Graphics2D g);

	public abstract void keyPressed(KeyEvent e, int k);

	public abstract void keyReleased(KeyEvent e, int k);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e, Point p);

	public abstract void mouseWheelMoved(MouseWheelEvent e);

}
