package project.board.piece;

import java.util.ArrayList;

import project.board.Board;
import project.board.Board.Direction;
import project.board.Position;

public class Pawn extends Piece {

	public Pawn(Board board, Direction targetDirection, int team) {
		super(board, targetDirection, team);
		sprite = 5;
		value = 1;
	}
	
	public ArrayList<Position> getPossibleMoves(boolean protection) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int dx = targetDirection == Direction.RIGHT ? 1 : -1;
		if (position.x + dx >= 0 && position.x + dx < board.getWidth()) {
			if (board.getPiece(position.x + dx, position.y) == null) {
				positions.add(new Position(position.x + dx, position.y));
				if (moves == 0 && position.x + dx * 2 >= 0 && position.x + dx * 2 < board.getWidth() && board.getPiece(position.x + dx * 2, position.y) == null) {
					positions.add(new Position(position.x + dx * 2, position.y));
				}
			}
			if (position.y + 1 >= 0 && position.y + 1 < board.getHeight()) {
				Piece piece = board.getPiece(position.x + dx, position.y + 1);
				if (piece != null && (piece.getTeam() != getTeam() || protection)) {
					positions.add(new Position(position.x + dx, position.y + 1));
				}
				/*piece = board.getPiece(position.x, position.y + 1);
				if (piece != null && piece instanceof Pawn && (piece.getTeam() != getTeam() || protection)) {
					positions.add(new Position(position.x + dx, position.y + 1));
					board.setPiece(null, position.x, position.y + 1);
				}*/
			}
			if (position.y - 1 >= 0 && position.y - 1 < board.getHeight()) {
				Piece piece = board.getPiece(position.x + dx, position.y - 1);
				if (piece != null && (piece.getTeam() != getTeam() || protection)) {
					positions.add(new Position(position.x + dx, position.y - 1));
				}
				/*piece = board.getPiece(position.x, position.y - 1);
				if (piece != null && piece instanceof Pawn && (piece.getTeam() != getTeam() || protection)) {
					positions.add(new Position(position.x + dx, position.y - 1));
					board.setPiece(null, position.x, position.y - 1);
				}*/
			}
		}
		return positions;
	}
	
	protected Piece create() {
		return new Pawn(board, targetDirection, team);
	}

	
}
