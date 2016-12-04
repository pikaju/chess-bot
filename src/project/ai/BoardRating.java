package project.ai;

import java.util.ArrayList;

import project.board.Board;
import project.board.Position;
import project.board.piece.Piece;

public class BoardRating {
	
	public static Board getBestMove(Board board, int team, int prediction) {
		ArrayList<Board> boards = getPossibleBoards(board, team);
		Board bestBoard = null;
		float rating = -Float.MAX_VALUE;
		for (int i = 0; i < boards.size(); i++) {
			Board result = boards.get(i);
			if (prediction > 0) {
				result = getBestMove(result, result.getActiveTeam(), prediction - 1);
			}
			float r = BoardRating.rateBoard(result, team);
			if (r > rating) {
				rating = r;
				bestBoard = boards.get(i);
			}
		}
		return bestBoard;
	}
	
	public static Board getBestMove(Board board, int team) {
		ArrayList<Board> boards = BoardRating.getPossibleBoards(board, team);
		Board bestBoard = null;
		float rating = -Float.MAX_VALUE;
		for (int i = 0; i < boards.size(); i++) {
			float r = BoardRating.rateBoard(boards.get(i), team);
			if (r > rating) {
				rating = r;
				bestBoard = boards.get(i);
			}
		}
		return bestBoard;
	}

	public static float rateBoard(Board board, int team) {
		float score = 0.0f;
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				Piece piece = board.getPiece(x, y);
				if (piece == null) continue;
				
				int value = piece.getValue();
				if (value == -1) value = 8192;
				if (piece.getTeam() == team) {
					score += value * 8.0f;
				} else {
					score -= value * 8.0f;
				}
				ArrayList<Position> moves = piece.getPossibleMoves(true);
				for (int i = 0; i < moves.size(); i++) {
					Piece target = board.getPiece(moves.get(i).x, moves.get(i).y);
					if (target == null) continue;
					
					value = target.getValue();
					if (value == -1) value = 8192;
					
					if (target.getTeam() == team) {
						if (piece.getTeam() == team) {
							score += target.getValue() / 8.0f;
						} else {
							score -= target.getValue() * 1.5f;
						}
					} else {
						if (piece.getTeam() == team) {
							score += target.getValue();
						} else {
							score -= target.getValue() / 8.0f;
						}
					}
				}
			}	
		}
		return score;
	}
	
	public static ArrayList<Board> getPossibleBoards(Board board, int team) {
		ArrayList<Board> boards = new ArrayList<Board>();
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				Piece piece = board.getPiece(x, y);
				if (piece == null) continue;
				if (piece.getTeam() != team) continue;
				
				ArrayList<Position> moves = piece.getPossibleMoves(false);
				for (int i = 0; i < moves.size(); i++) {
					Board b = new Board(board);
					b.getPiece(x, y).moveTo(moves.get(i).x, moves.get(i).y);
					boards.add(b);
				}
			}
		}
		return boards;
	}
	
}
