package com.Eagle.game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

public class Ball {

	public int x = 0, y = 0;
	private final int DIAMETER = 10;
	private static int xa = -1, ya = 1;

	private Game game;

	public int getXa() {
		return xa;
	}

	public Ball(int x, int y, Game g) {
		this.game = g;
		this.x = x / 2;
		this.y = y / 2;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 10, 10);

	}

	public void move() {
		if (x + xa < 0) game.p2.score();
		if (x + xa > game.getWidth() - DIAMETER) game.p1.score();
		if (y + ya < 0) ya = 1;
		if (y + ya > game.getHeight() - DIAMETER) ya = -1;

		if (collisionP1()) {
			xa = 1;
			x = game.p1.getX() + DIAMETER;
		} else if (collisionP2()) {
			xa = -1;
			x = game.p2.getX() - DIAMETER;
		}

		x += xa;
		y += ya;
	}

	public void reset() {
		game.p1.y = 90;
		game.p2.y = 90;
		game.ball.ya = 0;
		game.ball.xa = 0;

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ball position
		if (game.ball.x < game.WIDTH / 2) {
			game.ball.x = game.p1.x;
			game.ball.y = game.p1.yMiddle;
			game.ball.xa = -1;
			if (Math.random() < 0.5) game.ball.ya = 1;
			else game.ball.ya = -1;
		}
		if (game.ball.x > game.WIDTH / 2) {
			game.ball.x = game.p2.x;
			game.ball.y = game.p2.yMiddle;
			game.ball.xa = 1;
			if (Math.random() < 0.5) game.ball.ya = 1;
			else game.ball.ya = -1;
		}

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	public boolean collisionP1() {
		return game.p1.getBounds().intersects(getBounds());
	}

	public boolean collisionP2() {
		return game.p2.getBounds().intersects(getBounds());
	}

	public int getY() {
		return y;
	}

}
