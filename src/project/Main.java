package project;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import project.game.Game;
import project.io.Input;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static Main i;
	
	private JFrame frame;
	private Thread thread;
	private boolean running;
	
	private Game game;
	private Input input;

	public Main() {
		Dimension dimension = new Dimension(800, 600);
		setPreferredSize(dimension);
	}

	public static void main(String[] args) {
		i = new Main();
		i.start();
	}

	private void initialize() {
		frame = new JFrame("Chess Bot");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
		
		input = new Input();
		addMouseListener(input);
		addMouseMotionListener(input);
		
		game = new Game();
		
		frame.setVisible(true);
	}

	private void cleanup() {

	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		initialize();
		thread = new Thread(this, "Semester Projekt");
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		cleanup();
		System.exit(0);
	}

	public void run() {
		long currentTime = 0;
		long lastTime = System.currentTimeMillis();
		int timer = 0;
		float delta = 0.0f;
		int frames = 0;
		while (running) {
			currentTime = System.currentTimeMillis();
			timer += (int) (currentTime - lastTime);
			delta = (float) (currentTime - lastTime) / 1000.0f;
			lastTime = currentTime;

			update(delta);
			render();

			frames++;
			if (timer >= 1000) {
				timer -= 1000;
				System.out.println(frames + "fps");
				frames = 0;
			}
		}
	}

	private void update(float delta) {
		game.update(input, delta);
		input.refresh();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		game.render(g);
		
		g.dispose();
		bs.show();
	}
}
