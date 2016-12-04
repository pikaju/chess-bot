package project.board.piece;

import java.util.ArrayList;

import project.board.Board;
import project.board.Board.Direction;
import project.board.Position;

public class King extends Piece {

	public King(Board board, Direction targetDirection, int team) {
		super(board, targetDirection, team);
		sprite = 0;
		value = -1;
	}
	
	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int dx;
		int dy;
		
		dx = 1;
		dy = 0;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 1;
		dy = 1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 0;
		dy = 1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -1;
		dy = 1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -1;
		dy = 0;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -1;
		dy = -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 0;
		dy = -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 1;
		dy = -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		return positions;
	}
	
	protected Piece create() {
		return new King(board, targetDirection, team);
	}
	
}
