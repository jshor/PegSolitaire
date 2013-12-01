package aima.core.environment.pegsolitaire;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;


/**
 * @author Joshua Shor
 * 
 */
public class PegSolitaireFunctionFactory {
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			PegSolitaireBoard board = (PegSolitaireBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();
	
			for(int i=0; i<33; i++) {
				if (board.canMoveGap(PegSolitaireBoard.UP, i)) {
					actions.add(PegSolitaireBoard.UP);
				}
				if (board.canMoveGap(PegSolitaireBoard.DOWN, i)) {
					actions.add(PegSolitaireBoard.DOWN);
				}
				if (board.canMoveGap(PegSolitaireBoard.LEFT, i)) {
					actions.add(PegSolitaireBoard.LEFT);
				}
				if (board.canMoveGap(PegSolitaireBoard.RIGHT, i)) {
					actions.add(PegSolitaireBoard.RIGHT);
				}
			}
			
			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			// need to loop through all 33 holes and check if the peg can move or not
			PegSolitaireBoard board = (PegSolitaireBoard) s;

			for(int i=0; i<33; i++) {
				if (PegSolitaireBoard.UP.equals(a) && board.canMoveGap(PegSolitaireBoard.UP, i)) {
					PegSolitaireBoard newBoard = new PegSolitaireBoard(board);
					newBoard.moveGapUp(i);
					return newBoard;
				} else if (PegSolitaireBoard.DOWN.equals(a) && board.canMoveGap(PegSolitaireBoard.DOWN, i)) {
					PegSolitaireBoard newBoard = new PegSolitaireBoard(board);
					newBoard.moveGapDown(i);
					return newBoard;
				} else if (PegSolitaireBoard.LEFT.equals(a) && board.canMoveGap(PegSolitaireBoard.LEFT, i)) {
					PegSolitaireBoard newBoard = new PegSolitaireBoard(board);
					newBoard.moveGapLeft(i);
					return newBoard;
				} else if (PegSolitaireBoard.RIGHT.equals(a) && board.canMoveGap(PegSolitaireBoard.RIGHT, i)) {
					PegSolitaireBoard newBoard = new PegSolitaireBoard(board);
					newBoard.moveGapRight(i);
					return newBoard;
				}
			}
			
			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}