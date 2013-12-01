
public class State {
	private int[] tempBoard = new int[33];
	private Board board;
	private double F = 0; // Heuristic scores using Manhattan distance.
	
	public State(int[] b) {
		for(int i=0; i<b.length; i++)
			this.tempBoard[i] = b[i];
		
		board = new Board(tempBoard);
	}
	
	public double getF() {
		return this.F;
	}
	
	/**
	 * Searches the four (or fewer) possibilities for a node n.
	 * Calculates a new heuristic score F (if new state cannot be found using k, return -1)
	 * @param n The current board's peg to search.
	 * @param k The direction to search (0=right, 1=down, 2=left, 3=top)
	 */
	public void searchDirection(int n, int k) {
		/* Check if the position is a hole on the board, is not occupied by another peg and a peg can be jumped over to get to it (in that order) */
		//System.out.println("n,k=" + n + "," + k);
		if(Game.openPos[n][k] > -1 && this.board.isOccupied(Game.openPos[n][k]) == false && this.board.isOccupied(Game.adjPos[n][k]) == true) {
			/* Change the distance of the moving peg with respect to H (distance to goal) */

			// calculate average distance between pegs, with respect to the center hole.
			this.F = this.board.pegCount()*this.board.pegCount();
			
			/* Move the peg */
			this.board.occupy(Game.openPos[n][k], 1); // Peg is now in the new hole.
			this.board.occupy(n, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			this.board.occupy(Game.adjPos[n][k], 0);

		} else {
			// the peg cannot move, so this state is unchanging -- set F = -1.
			this.F = -1;
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Prints the board.
	 * @return String user-friendly representation of the board.
	 */
	public String printBoard() {
		return board.toString();
	}
}
