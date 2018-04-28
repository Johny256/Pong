import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {

	public int x, y, score;
	public static final int WIDTH = 5, HEIGHT = 30;
	public int yMiddle;
	private int currYMiddle;
	private int ya = 0;
	private Game game;

	public Paddle(int x, int y, Game g, int score) {
		this.x = x;
		this.y = y;
		this.game = g;
		this.score = score;
		yMiddle = y + (HEIGHT / 2);

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);

	}

	public void move() {
		currYMiddle = y + (HEIGHT / 2);
		if (y + ya > 0 && y + ya < game.getHeight() - 28) y += ya;
	}

	public void moveAI() {
		currYMiddle = y + (HEIGHT / 2);
		if (game.ball.getBounds().intersects(game.lineBounds()) && game.ball.getXa() == 1) {
			if (game.ball.getY() > currYMiddle) ya = 1;
			if (game.ball.getY() < currYMiddle) ya = -1;
		} else
			ya = 0;

		if (y + ya > 0 && y + ya < game.getHeight() - 28) y += ya;

	}

	public void score() {
		score++;
		game.ball.reset();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) ya = -1;
		if (key == KeyEvent.VK_DOWN) ya = 1;

	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public int getX() {
		return x;
	}

}
