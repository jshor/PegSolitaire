
/**
 * @author Josh Shor
 *
 */
public class State {
	private int[] boardList = new int[33];
	private Board board;
	private int lastPeg = 0;
	private int lastDirection = 0;
	
	public State(State oldState) {
		int b[] = oldState.getList();
		
		for(int i=0; i<b.length; i++)
			this.boardList[i] = (b[i] == 1 ? 1 : 0);
		
		this.lastDirection = oldState.getLastDirection();
		this.lastPeg = oldState.getLastPeg();
		
		board = new Board(boardList);
	}
	
	public State(int[] b) {
		for(int i=0; i<b.length; i++)
			this.boardList[i] = b[i];
		
		this.lastDirection = 0;
		this.lastPeg = 0;
		
		board = new Board(boardList);
	}
	
	/**
	 * Searches the four (or fewer) possibilities for a node n.
	 * Calculates a new heuristic score F (if new state cannot be found using k, return -1)
	 * @param n The current board's peg to search.
	 * @param k The direction to search (0=right, 1=down, 2=left, 3=top)
	 * @return 
	 */
	public boolean searchDirection() {
		/* Check if the position is a hole on the board, is not occupied by another peg and a peg can be jumped over to get to it (in that order) */
		int n = this.lastPeg;
		int k = this.lastDirection;
		
		//System.out.println("Looking at board: ");
		//System.out.println(this.printBoard());
		
		if(Game.openPos[n][k] > -1 && this.board.isOccupied(Game.openPos[n][k]) == false && this.board.isOccupied(Game.adjPos[n][k]) == true) {
			/* Move the peg */
			this.board.occupy(Game.openPos[n][k], 1); // Peg is now in the new hole.
			this.board.occupy(n, 0); // Peg is no longer in the old hole.
			/* remove the peg that was jumped */
			this.board.occupy(Game.adjPos[n][k], 0);
			
			System.out.println("Add peg to: " + Game.openPos[n][k] + ", remove peg from: " + n + ", remove peg from: " + Game.adjPos[n][k]);
			
			System.out.println(this.printBoard() + "\n-------------------------------------------------");
			System.out.println("Peg count: " + this.board.pegCount());
			return true;
		} else {
			return false;
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public int[] getList() {
		return this.boardList;
	}
	
	/**
	 * Prints the board.
	 * @return String user-friendly representation of the board.
	 */
	public String printBoard() {
		return board.toString();
	}

	public int getLastPeg() {
		return this.lastPeg;
	}

	public int getLastDirection() {
		return this.lastDirection;
	}

	public void setLastPeg(int i) {
		this.lastPeg = i;
	}

	public void setLastDirection(int i) {
		this.lastDirection = i;
	}
}
