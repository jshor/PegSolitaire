package aima.core.environment.pegsolitaire;

import aima.core.search.framework.GoalTest;


/**
 * @author Joshua Shor
 * 
 */
public class PegSolitaireGoalTest implements GoalTest {
	PegSolitaireBoard goal = new PegSolitaireBoard(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });

	public boolean isGoalState(Object state) {
		PegSolitaireBoard board = (PegSolitaireBoard) state;
		return board.equals(goal);
	}
}