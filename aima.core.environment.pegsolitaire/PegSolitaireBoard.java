package aima.core.environment.pegsolitaire;

import java.util.ArrayList;
import java.util.List;

import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;


/**
 * @author Joshua Shor
 * @author Ravi Mohan
 * 
 */
public class PegSolitaireBoard {

	public static Action LEFT = new DynamicAction("Left");

	public static Action RIGHT = new DynamicAction("Right");

	public static Action UP = new DynamicAction("Up");

	public static Action DOWN = new DynamicAction("Down");

	private int[] state;

	//
	// PUBLIC METHODS
	//

	public PegSolitaireBoard() {
		state = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	}
	
	public PegSolitaireBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public PegSolitaireBoard(PegSolitaireBoard copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}
	
	/**
	 * Occupy or vacate a hole.
	 * @param int n current peg to change value of
	 * @param int k value to change peg to (1 or 0)
	 */
	public void occupy(int n, int k) {
		this.state[n] = k;
	}	
	
	/**
	 * @param int n current peg
	 * @return boolean value of whether a hole is occupied or not.
	 */
	public boolean isOccupied(int n) {
		return (this.state[n] == 1 ? true : false);
	}

	public void moveGapRight(int gapPos) {
		if(PegSolitaire.openPos[gapPos][0] > -1 && isOccupied(PegSolitaire.openPos[gapPos][0]) == false && isOccupied(PegSolitaire.adjPos[gapPos][0]) == true) {
			/* Move the peg */
			occupy(PegSolitaire.openPos[gapPos][0], 1); // Peg is now in the new hole.
			occupy(gapPos, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			occupy(PegSolitaire.adjPos[gapPos][0], 0);
		}
	}
	
	public void moveGapDown(int gapPos) {
		if(PegSolitaire.openPos[gapPos][1] > -1 && isOccupied(PegSolitaire.openPos[gapPos][1]) == false && isOccupied(PegSolitaire.adjPos[gapPos][1]) == true) {
			/* Move the peg */
			occupy(PegSolitaire.openPos[gapPos][1], 1); // Peg is now in the new hole.
			occupy(gapPos, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			occupy(PegSolitaire.adjPos[gapPos][1], 0);
		}
	}
	
	public void moveGapLeft(int gapPos) {
		if(PegSolitaire.openPos[gapPos][2] > -1 && isOccupied(PegSolitaire.openPos[gapPos][2]) == false && isOccupied(PegSolitaire.adjPos[gapPos][2]) == true) {
			/* Move the peg */
			occupy(PegSolitaire.openPos[gapPos][2], 1); // Peg is now in the new hole.
			occupy(gapPos, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			occupy(PegSolitaire.adjPos[gapPos][2], 0);
		}
	}
	
	public void moveGapUp(int gapPos) {
		if(PegSolitaire.openPos[gapPos][3] > -1 && isOccupied(PegSolitaire.openPos[gapPos][3]) == false && isOccupied(PegSolitaire.adjPos[gapPos][3]) == true) {
			/* Move the peg */
			occupy(PegSolitaire.openPos[gapPos][3], 1); // Peg is now in the new hole.
			occupy(gapPos, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			occupy(PegSolitaire.adjPos[gapPos][3], 0);
		}
	}

	public boolean canMoveGap(Action where, int gapPos) {
		boolean retVal = false;
		
		if(PegSolitaire.openPos[gapPos][0] > -1 && isOccupied(PegSolitaire.openPos[gapPos][0]) == false && isOccupied(PegSolitaire.adjPos[gapPos][0]) == true) {
			retVal = true;
		} else if(PegSolitaire.openPos[gapPos][1] > -1 && isOccupied(PegSolitaire.openPos[gapPos][1]) == false && isOccupied(PegSolitaire.adjPos[gapPos][1]) == true) {
			retVal = true;
		} else if(PegSolitaire.openPos[gapPos][2] > -1 && isOccupied(PegSolitaire.openPos[gapPos][2]) == false && isOccupied(PegSolitaire.adjPos[gapPos][2]) == true) {
			retVal = true;
		} else if(PegSolitaire.openPos[gapPos][3] > -1 && isOccupied(PegSolitaire.openPos[gapPos][3]) == false && isOccupied(PegSolitaire.adjPos[gapPos][3]) == true) {
			retVal = true;
		}
		
		return retVal;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		PegSolitaireBoard aBoard = (PegSolitaireBoard) o;

		for (int i = 0; i < 33; i++) {
			if (this.isOccupied(i) != aBoard.isOccupied(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 17;
		for (int i = 0; i < 33; i++) {
			int position = i;
			result = 37 * result + position;
		}
		return result;
	}

	/**
	 * @param int n current peg to look at
	 * @return String "x" or "o" value, depending on whether a peg is there or not.
	 */
	public String x(int n) {
		return (this.state[n] == 1 ? " x" : " o");
	}
	
	/**
	 * @return String prints the board
	 * @param q what values to print from the board
	 */
	@Override
	public String toString() {
		String boardStr = "";
		boardStr += "       " + x(0) + " " + x(1) + " " + x(2) + "\n";
		boardStr += "       " + x(3) + " " + x(4) + " " + x(5) + "\n";
		boardStr += " " + x(6) + " " + x(7) + " " + x(8) + " " + x(9) + " " + x(10) + " " + x(11) + " " + x(12) + "\n";
		boardStr += " " + x(13) + " " + x(14) + " " + x(15) + " " + x(16) + " " + x(17) + " " + x(18) + " " + x(19) + "\n";
		boardStr += " " + x(20) + " " + x(21) + " " + x(22) + " " + x(23) + " " + x(24) + " " + x(25) + " " + x(26) + "\n";
		boardStr += "       " + x(27) + " " + x(28) + " " + x(29) + "\n";
		boardStr += "       " + x(30) + " " + x(31) + " " + x(32) + "\n";
		boardStr += "\n";
		
		return boardStr;
	}
}