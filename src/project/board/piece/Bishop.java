package project.board.piece;

import java.util.ArrayList;

import project.board.Board;
import project.board.Board.Direction;
import project.board.Position;

public class Bishop extends Piece {

	public Bishop(Board board, Direction targetDirection, int team) {
		super(board, targetDirection, team);
		sprite = 2;
		value = 3;
	}

	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
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
		return new Bishop(board, targetDirection, team);
	}

}
