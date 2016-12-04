package project.game;

import java.awt.Graphics2D;

import project.board.Board;
import project.io.Input;

public class Game {

	private Board board;
	
	public Game() {
		board = new Board();
	}
	
	public void update(Input input, float delta) {
		board.update(input, delta);
	}
	
	public void render(Graphics2D g) {
		board.render(g);
	}
}
