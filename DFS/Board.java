
/**
 * @author Josh Shor
 *
 */

public class Board {
	private int[] board = new int[33];
	
	public Board(int[] b) {
		for(int i=0; i<b.length; i++)
			this.board[i] = b[i];
	}
	
	/**
	 * Occupy or vacate a hole.
	 * @param int n current peg to change value of
	 * @param int k value to change peg to (1 or 0)
	 */
	public void occupy(int n, int k) {
		this.board[n] = k;
	}
	
	/**
	 * @param int n current peg
	 * @return boolean value of whether a hole is occupied or not.
	 */
	public boolean isOccupied(int n) {
		return (board[n] == 1 ? true : false);
	}
	
	public int[] getList() {
		return board;
	}
	
	// number of pegs on board
	public int pegCount() {
		int c = 0;
		for(int i=0; i<this.board.length; i++)
			c += this.board[i];
		return c;
	}
	
	
	/**
	 * @param int n current peg to look at
	 * @return String "x" or "o" value, depending on whether a peg is there or not.
	 */
	private String x(int n) {
		return (this.board[n] == 1 ? " x" : " o");
	}
	
	private int pos(int n) {
		return this.board[n];
	}
	
	/**
	 * @return String prints the board
	 * @param q what values to print from the board
	 */
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
