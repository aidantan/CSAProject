package unit15;
//� A+ Computer Science  -  www.apluscompsci.com

//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class Pong extends Canvas implements KeyListener, Runnable {
	private Ball ball;
	private Paddle paddle;

	private boolean[] keys;
	private BufferedImage back;
	private int leftScore;
	private int rightScore;

	private static final int ballx = 380;
	private static final int bally = 265;

	public Pong() {
		// set up all variables related to the game
		ball = new Ball(ballx, bally, 10, 10, Color.BLUE);

		paddle = new Paddle(600, 244, 40, 40, Color.YELLOW, 2);

		keys = new boolean[4];
		leftScore = 0;
		rightScore = 0;

		setBackground(Color.WHITE);
		setVisible(true);

		new Thread(this).start();
		addKeyListener(this); // starts the key thread to log key strokes
	}

	public void update(Graphics window) {
		paint(window);
	}

	public void paint(Graphics window) {
		// set up the double buffering to make the game animation nice and
		// smooth
		Graphics2D twoDGraph = (Graphics2D) window;

		// take a snap shop of the current screen and same it as an image
		// that is the exact same width and height as the current screen
		if (back == null)
			back = (BufferedImage) (createImage(getWidth(), getHeight()));

		// create a graphics reference to the back ground image
		// we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		// draw Objectsc
		ball.moveAndDraw(graphToBack);
		paddle.draw(graphToBack);

		// see if ball hits left wall or right wall
		if ((ball.getX() <= 10)) {
		
			ball.setXSpeed(-ball.getXSpeed());

		}
		if (ball.getX() >= 780) {
			ball.setXSpeed(-ball.getXSpeed());
		}
		
		if (ball.getBounds().intersects(paddle.getBounds())) {
			for (int i = 0; i < paddle.getFullBounds().size(); i++) {
				if (ball.getBounds().intersects(paddle.getFullBounds().get(i))) {
					if (i == paddle.TOP_LEFT || i == paddle.TOP_RIGHT || i == paddle.BOTTOM_LEFT || i == paddle.BOTTOM_RIGHT) {
						ball.setSpeed(-ball.getXSpeed(), -ball.getYSpeed());
					} else if (i == paddle.LEFT_MIDDLE || i == paddle.RIGHT_MIDDLE) {
						ball.setXSpeed(-ball.getXSpeed());
					} else if (i == paddle.BOTTOM_MIDDLE || i == paddle.TOP_MIDDLE) {
						ball.setYSpeed(-ball.getYSpeed());
					}
				}
			}
		}
		
		// see if the ball hits the top or bottom wall
		if ((ball.getY() <= 10 || ball.getY() >= 550)) {
			ball.setYSpeed(-ball.getYSpeed());
		}
		graphToBack.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		graphToBack.clearRect(1, 556, 799, 247);
		graphToBack.setColor(Color.RED);
		graphToBack.drawString("Player 1 Score: " + leftScore + "                                       "
				+ "Player 2 Score: " + rightScore, 70, 590);
		

		// see if the paddles need to be moved
		if (keys[0] == true) {
			paddle.moveUpAndDraw(graphToBack);
		}
		if (keys[1] == true) {
			paddle.moveLeftAndDraw(graphToBack);
		}

		if (keys[2] == true) {
			paddle.moveDownAndDraw(graphToBack);
		}
		if (keys[3] == true) {
			paddle.moveRightAndDraw(graphToBack);
		}

		twoDGraph.drawImage(back, null, 0, 0);
	}

	public void keyPressed(KeyEvent e) {
		switch (toUpperCase(e.getKeyChar())) {
		case 'W':
			keys[0] = true;
			break;
		case 'A':
			keys[1] = true;
			break;
		case 'S':
			keys[2] = true;
			break;
		case 'D':
			keys[3] = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (toUpperCase(e.getKeyChar())) {
		case 'W':
			keys[0] = false;
			break;
		case 'A':
			keys[1] = false;
			break;
		case 'S':
			keys[2] = false;
			break;
		case 'D':
			keys[3] = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(8);
				repaint();
			}
		} catch (Exception e) {
		}
	}
}