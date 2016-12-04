package project.board.piece;

import java.util.ArrayList;

import project.board.Board;
import project.board.Board.Direction;
import project.board.Position;

public class Knight extends Piece {

	public Knight(Board board, Direction targetDirection, int team) {
		super(board, targetDirection, team);
		sprite = 3;
		value = 3;
	}

	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int dx;
		int dy;
		
		dx = 2;
		dy = 1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 2;
		dy = -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 1;
		dy = 2;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -1;
		dy = 2;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -2;
		dy = 1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -2;
		dy = -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = 1;
		dy = -2;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		dx = -1;
		dy = -2;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth() && position.y + dy >= 0 && position.y + dy < board.getHeight()) {
			Piece piece = board.getPiece(position.x + dx, position.y + dy);
			if (piece == null || (piece.getTeam() != getTeam() || protection)) positions.add(new Position(position.x + dx, position.y + dy));
		}
		return positions;
	}
	
	protected Piece create() {
		return new Knight(board, targetDirection, team);
	}

}
