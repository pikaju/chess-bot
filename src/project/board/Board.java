package project.board;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import project.Main;
import project.ai.BoardRating;
import project.board.piece.Bishop;
import project.board.piece.King;
import project.board.piece.Knight;
import project.board.piece.Pawn;
import project.board.piece.Piece;
import project.board.piece.Queen;
import project.board.piece.Rook;
import project.io.Input;

public class Board {

	public static final int BOARD_SIZE = 512;
	
	public enum Direction {
		LEFT, RIGHT
	}
	
	public Piece[][] pieces;
	public BufferedImage piecesSpriteSheet;
	public int piecesSpritesX = 6;
	public int piecesSpritesY = 2;
	
	private int hoverX;
	private int hoverY;
	private int selectedX;
	private int selectedY;
	
	private ArrayList<Position> selectedMoves;
	private ArrayList<ArrayList<Position>> possibleMoves;
	
	private int activeTeam;
	private int numTeams;
	
	private float timer = 0.0f;
	
	public Board() {
		pieces = new Piece[8][8];
		initializePieces();
		try {
			piecesSpriteSheet = ImageIO.read(Board.class.getResourceAsStream("/pieces.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		selectedX = -1;
		selectedY = -1;
		activeTeam = 0;
		numTeams = 2;
		possibleMoves = new ArrayList<ArrayList<Position>>();
		for (int i = 0; i < numTeams; i++) {
			possibleMoves.add(null);
		}
	}
	
	public Board(Board board) {
		apply(board);
	}

	public void update(Input input, float delta) {
		int boardX = Main.i.getWidth() / 2 - BOARD_SIZE / 2;
		int boardY = Main.i.getHeight() / 2 - BOARD_SIZE / 2;
		int boardW = BOARD_SIZE;
		int boardH = BOARD_SIZE;
		hoverX = (int) Math.floor((float) (input.getX() - boardX) / (float) boardW * pieces.length);
		hoverY = (int) Math.floor((float) (input.getY() - boardY) / (float) boardH * pieces[0].length);
		if (hoverX >= 0 && hoverX < pieces.length && hoverY >= 0 && hoverY < pieces[0].length) {
			if (input.getButton(Input.BUTTON_LEFT)) {
				if (selectedX == -1 && selectedY == -1) {
					if (getPiece(hoverX, hoverY) != null && getPiece(hoverX, hoverY).getTeam() == activeTeam) {
						selectedX = hoverX;
						selectedY = hoverY;
						selectedMoves = getPiece(selectedX, selectedY).getPossibleMoves(false);
					}
				} else {
					for (int i = 0; i < selectedMoves.size(); i++) {
						if (hoverX == selectedMoves.get(i).x && hoverY == selectedMoves.get(i).y) {
							getPiece(selectedX, selectedY).moveTo(hoverX, hoverY);
							// TODO: Updates possibleModes
							break;
						}
					}
					selectedX = -1;
					selectedY = -1;
					selectedMoves = null;
				}
			}
		}
		for (int x = 0; x < pieces.length; x++) {
			for (int y = 0; y < pieces[0].length; y++) {
				if (getPiece(x, y) != null) {
					getPiece(x, y).update(delta);
				}
			}
		}
		if (activeTeam == 1) {
			timer += delta;
		}
		if (timer > 0.5f) {
			timer = 0.0f;
			apply(BoardRating.getBestMove(this, activeTeam, 2));
		}
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		int boardX = Main.i.getWidth() / 2 - BOARD_SIZE / 2;
		int boardY = Main.i.getHeight() / 2 - BOARD_SIZE / 2;
		int boardW = BOARD_SIZE;
		int boardH = BOARD_SIZE;
		g.fillRect(boardX - 3, boardY - 3, boardW + 6, boardH + 6);
		for (int x = 0; x < pieces.length; x++) {
			for (int y = 0; y < pieces[0].length; y++) {
				if ((x + y) % 2 == 0) {
					g.setColor(new Color(0x755449));
				} else {
					g.setColor(new Color(0xB3A396));
				}
				g.fillRect(Main.i.getWidth() / 2 - BOARD_SIZE / 2 + BOARD_SIZE / pieces.length * x, Main.i.getHeight() / 2 - BOARD_SIZE / 2 + BOARD_SIZE / pieces[0].length * y, BOARD_SIZE / pieces.length, BOARD_SIZE / pieces[0].length);
			}
		}
		if (selectedMoves != null) {
			g.setColor(new Color(0x503050f0, true));
			for (int i = 0; i < selectedMoves.size(); i++) {
				g.fillRect(Main.i.getWidth() / 2 - BOARD_SIZE / 2 + BOARD_SIZE / pieces.length * selectedMoves.get(i).x, Main.i.getHeight() / 2 - BOARD_SIZE / 2 + BOARD_SIZE / pieces[0].length * selectedMoves.get(i).y, BOARD_SIZE / pieces.length, BOARD_SIZE / pieces[0].length);
			}
		}
		if (selectedX >= 0 && selectedX < pieces.length && selectedY >= 0 && selectedY < pieces[0].length) {
			g.setColor(Color.WHITE);
			g.fillRect(selectedX * boardW / pieces.length + boardX, selectedY * boardH / pieces[0].length + boardY, boardW / pieces.length, boardH / pieces[0].length);
		}
		for (int x = 0; x < pieces.length; x++) {
			for (int y = 0; y < pieces[0].length; y++) {
				if (pieces[x][y] != null) {
					pieces[x][y].render(g);
				}
			}
		}
		if (hoverX >= 0 && hoverX < pieces.length && hoverY >= 0 && hoverY < pieces[0].length) {
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(4.0f));
			g.drawRect(hoverX * boardW / pieces.length + boardX, hoverY * boardH / pieces[0].length + boardY, boardW / pieces.length - 1, boardH / pieces[0].length - 1);
			g.setStroke(new BasicStroke(1.0f));
		}
	}
	
	
	public void moveTo(Piece piece, int x, int y) {
		pieces[piece.getX()][piece.getY()] = null;
		setPiece(piece, x, y);
		activeTeam = (activeTeam + 1) % numTeams;
	}

	public void setPiece(Piece piece, int x, int y) {
		pieces[x][y] = piece;
		piece.setPosition(x, y);
		piece.setBoard(this);
	}
	
	public Piece getPiece(int x, int y) {
		return pieces[x][y];
	}
	
	public void initializePieces() {
		setPiece(new Rook(this, Direction.RIGHT, 0), 0, 0);
		setPiece(new Rook(this, Direction.RIGHT, 0), 0, 7);
		setPiece(new Rook(this, Direction.LEFT, 1), 7, 0);
		setPiece(new Rook(this, Direction.LEFT, 1), 7, 7);
		
		setPiece(new Knight(this, Direction.RIGHT, 0), 0, 1);
		setPiece(new Knight(this, Direction.RIGHT, 0), 0, 6);
		setPiece(new Knight(this, Direction.LEFT, 1), 7, 1);
		setPiece(new Knight(this, Direction.LEFT, 1), 7, 6);
		
		setPiece(new Bishop(this, Direction.RIGHT, 0), 0, 2);
		setPiece(new Bishop(this, Direction.RIGHT, 0), 0, 5);
		setPiece(new Bishop(this, Direction.LEFT, 1), 7, 2);
		setPiece(new Bishop(this, Direction.LEFT, 1), 7, 5);
		
		setPiece(new Queen(this, Direction.RIGHT, 0), 0, 3);
		setPiece(new King(this, Direction.RIGHT, 0), 0, 4);
		setPiece(new Queen(this, Direction.LEFT, 1), 7, 3);
		setPiece(new King(this, Direction.LEFT, 1), 7, 4);
		
		for (int y = 0; y < pieces[0].length; y++) {
			setPiece(new Pawn(this, Direction.RIGHT, 0), 1, y);
			setPiece(new Pawn(this, Direction.LEFT, 1), 7 - 1, y);
		}
	}
	
	public void apply(Board board) {
		pieces = new Piece[board.pieces.length][board.pieces[0].length];
		for (int x = 0; x < pieces.length; x++) {
			for (int y = 0; y < pieces[0].length; y++) {
				if (board.getPiece(x, y) != null) {
					setPiece(board.getPiece(x, y).copy(), x, y);
				}
			}
		}
		piecesSpriteSheet = board.piecesSpriteSheet;
		selectedX = board.selectedX;
		selectedY = board.selectedY;
		activeTeam = board.activeTeam;
		numTeams = board.numTeams;
	}
	
	public int getWidth() {
		return pieces.length;
	}
	
	public int getHeight() {
		return pieces[0].length;
	}
	
	public int getActiveTeam() {
		return activeTeam;
	}
}
