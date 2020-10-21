package framework.inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;

import framework.PlayState;

public class Button extends Paintable {

    private boolean mouseOver;
    private Runnable clickAction;
    private String text;

    public Button(int x, int y, int width, int height, Color color, Runnable clickAction, String text) {
	super(x, y, width, height, color);
	this.clickAction = clickAction;
	this.text = text;
    }

    @Override
    public void render(Graphics2D g) {
	Point mouse = PlayState.mousePosition;
	g.setColor(Color.blue);
	if (mouse.x > x && mouse.x < x + width) {
	    if (mouse.y > y && mouse.y < y + height) {
		mouseOver = true;
	    } else
		mouseOver = false;
	} else
	    mouseOver = false;
	Font oldFont = g.getFont();
	g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, height / 2));
	int h = g.getFontMetrics().getHeight();
	int w = g.getFontMetrics().stringWidth(text);
	g.setColor(color);
	g.fillRect(x, y, width, height);
	g.setColor(color.darker());
	Stroke oldStroke = g.getStroke();
	g.setStroke(new BasicStroke(2.0f));
	g.drawRect(x, y, width, height);
	g.setStroke(oldStroke);
	g.drawString(text, x + width / 2 - w / 2, (y - h / 2) + height);
	g.setFont(oldFont);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON1) {
	    if (mouseOver) {
		clickAction.run();
	    }
	}
    }
}
