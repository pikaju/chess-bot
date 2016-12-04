package project.board.piece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import project.Main;
import project.board.Board;
import project.board.Position;

public class Piece {

	protected Board board;
	protected Position position;
	protected Board.Direction targetDirection;
	protected int team;
	protected int sprite;
	protected boolean flipSprite;
	protected int value;
	protected int moves;
	
	private float xAnim;
	private float yAnim;
	
	public Piece(Board board, Board.Direction targetDirection, int team) {
		this.board = board;
		position = new Position(-1, -1);
		this.targetDirection = targetDirection;
		this.team = team;
		moves = 0;
		flipSprite = targetDirection == Board.Direction.RIGHT;
	}

	public void update(float delta) {
		xAnim *= Math.pow(0.0005f, delta);
		yAnim *= Math.pow(0.0005f, delta);
	}
	
	public void render(Graphics2D g) {
		int spriteX = sprite * board.piecesSpriteSheet.getWidth() / board.piecesSpritesX;
		int spriteY = board.piecesSpriteSheet.getHeight() / board.piecesSpritesY * team;
		
		int x = position.x * Board.BOARD_SIZE / 8 + Main.i.getWidth() / 2 - Board.BOARD_SIZE / 2;
		int y = position.y * Board.BOARD_SIZE / 8 + Main.i.getHeight() / 2 - Board.BOARD_SIZE / 2;
		int width = Board.BOARD_SIZE / 8;
		int height = Board.BOARD_SIZE / 8;

		if (getTeam() == board.getActiveTeam()) {
			g.setColor(new Color(0x1040ff40, true));
			g.fillRect(x, y, width + 1, height + 1);
		}
		
		x += xAnim * width;
		y += yAnim * height;
		
		if (flipSprite) {
			x += width;
			width = -width;
		}
		
		g.drawImage(board.piecesSpriteSheet, x, y, x + width, y + height, spriteX, spriteY, spriteX + board.piecesSpriteSheet.getWidth() / board.piecesSpritesX, spriteY + board.piecesSpriteSheet.getHeight() / board.piecesSpritesY, null);
	}
	
	public void moveTo(int x, int y) {
		xAnim = position.x - x;
		yAnim = position.y - y;
		board.moveTo(this, x, y);
		moves++;
	}
	
	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}
	
	public void setPosition(int x, int y) {
		position.x = x;
		position.y = y;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getTeam() {
		return team;
	}
	
	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
		return positions;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	protected Piece create() {
		throw new RuntimeException("Can't copy default piece");
	}
	
	public Piece copy() {
		Piece piece = create();
		piece.position.x = position.x;
		piece.position.y = position.y;
		piece.xAnim = xAnim;
		piece.yAnim = yAnim;
		piece.moves = moves;
		return piece;
	}
}
