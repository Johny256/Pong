package com.eagle256.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	public static final int WIDTH = 512, HEIGHT = 256;

	public Paddle p1 = new Paddle(10, 90, this, 0);
	public Paddle p2 = new Paddle(WIDTH - 20, 90, this, 0);
	public Ball ball = new Ball(WIDTH, HEIGHT, this);

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// empty method
			}

			@Override
			public void keyReleased(KeyEvent e) {
				p1.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				p1.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		p1.move();
		p2.moveAI();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.white);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());

		// draw score
		String p1Score = Integer.toString(p1.score);
		String p2Score = Integer.toString(p2.score);

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g2d.drawString(p1Score, WIDTH / 2 - 50, 30);
		g2d.drawString(p2Score, WIDTH / 2 + 5, 30);

		p1.paint(g2d);
		p2.paint(g2d);
		ball.paint(g2d);

	}

	public Rectangle lineBounds() {
		return new Rectangle(getWidth() / 2, 0, getWidth() / 2, getHeight());
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Pong");
		Game game = new Game();

		f.add(game);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		while (true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
