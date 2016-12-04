package project.board.piece;

import java.util.ArrayList;

import project.board.Board;
import project.board.Board.Direction;
import project.board.Position;

public class Queen extends Piece {

	public Queen(Board board, Direction targetDirection, int team) {
		super(board, targetDirection, team);
		sprite = 1;
		value = 9;
	}
	
	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
		for (int x = position.x - 1; x >= 0; x--) {
			Piece piece = board.getPiece(x, position.y);
			if (piece == null) {
				positions.add(new Position(x, position.y));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(x, position.y));
			}
			break;
		}
		for (int x = position.x + 1; x < board.getWidth(); x++) {
			Piece piece = board.getPiece(x, position.y);
			if (piece == null) {
				positions.add(new Position(x, position.y));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(x, position.y));
			}
			break;
		}
		for (int y = position.y - 1; y >= 0; y--) {
			Piece piece = board.getPiece(position.x, y);
			if (piece == null) {
				positions.add(new Position(position.x, y));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x, y));
			}
			break;
		}
		for (int y = position.y + 1; y < board.getHeight(); y++) {
			Piece piece = board.getPiece(position.x, y);
			if (piece == null) {
				positions.add(new Position(position.x, y));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x, y));
			}
			break;
		}
		
		int i = 0;
		while (true) {
			i++;
			if (position.x - i < 0 || position.y - i < 0) break;
			Piece piece = board.getPiece(position.x - i, position.y - i);
			if (piece == null) {
				positions.add(new Position(position.x - i, position.y - i));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x - i, position.y - i));
			}
			break;
		}
		i = 0;
		while (true) {
			i++;
			if (position.x + i >= board.getWidth() || position.y - i < 0) break;
			Piece piece = board.getPiece(position.x + i, position.y - i);
			if (piece == null) {
				positions.add(new Position(position.x + i, position.y - i));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x + i, position.y - i));
			}
			break;
		}
		i = 0;
		while (true) {
			i++;
			if (position.x - i < 0 || position.y + i >= board.getHeight()) break;
			Piece piece = board.getPiece(position.x - i, position.y + i);
			if (piece == null) {
				positions.add(new Position(position.x - i, position.y + i));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x - i, position.y + i));
			}
			break;
		}
		i = 0;
		while (true) {
			i++;
			if (position.x + i >= board.getWidth() || position.y + i >= board.getHeight()) break;
			Piece piece = board.getPiece(position.x + i, position.y + i);
			if (piece == null) {
				positions.add(new Position(position.x + i, position.y + i));
				continue;
			}
			if (piece.getTeam() != getTeam() || protection) {
				positions.add(new Position(position.x + i, position.y + i));
			}
			break;
		}
		return positions;
	}
	
	protected Piece create() {
		return new Queen(board, targetDirection, team);
	}


}
