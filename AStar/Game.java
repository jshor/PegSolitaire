import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
 
public class Game {
 
	/* Sort priorityQueue method */
    static class sortState implements Comparator<State> {
 
        public int compare(State one, State two) {
            return (int) Math.round(Math.round(one.getF()) - Math.round(two.getF()));
        }
    }
	
	//  Positions on the peg board are defined as follows:
	//
	//            0  1  2              Initially pegs
	//            3  4  5              exist in all holes
	//      6  7  8  9 10 11 12        except the user's choice for the
	//     13 14 15 16 17 18 19        start hole which is empty.
	//     20 21 22 23 24 25 26
	//           27 28 29
	//           30 31 32
	/* Positions respective to any hole are defined by: {RIGHT, DOWN, LEFT, UP} */
	/* Available moving positions for each hole (after jumping). If no position is available, it is -1. */
	public static int[][] openPos = {
		{2, 8, -1, -1}, {-1, 9, -1, -1}, {-1, 10, 0, -1}, {5, 15, -1, -1}, {-1, 16, -1, -1}, {-1, 17, 3, -1}, {8, 20, -1, -1}, {9, 21, -1, -1}, 
		{10, 22, 6, 0}, {11, 23, 7, 1}, {12, 24, 8, 2}, {-1, 25, 9, -1}, {-1, 26, 10, -1}, {15, -1, -1, -1}, {16, -1, -1, -1}, {17, 27, 13, 3},
		{18, 28, 14, 4}, {19, 29, 15, 5}, {-1, -1, 16, -1}, {-1, -1, 17, -1}, {22, -1, -1, 6}, {23, -1, -1, 7}, {24, 30, 20, 8}, {25, 31, 21, 9},
		{26, 32, 22, 10}, {-1, -1, 23, 11}, {-1, -1, 24, 12}, {29, -1, -1, 15}, {-1, -1, -1, 16}, {-1, -1, 27, 17}, {32, -1, -1, 22}, {-1, -1, -1, 23}, {-1, -1, 30, 24}
	};
	/* Adjacent positions for each hole using Manhattan. If no position is available, it is -1. */
	public static int[][] adjPos = {
		{1, 3, -1, -1}, {2, 4, 0, -1}, {-1, 5, 1, -1},  {4, 8, -1, 0}, {5, 9, 3, 1}, {-1, 10, 4, 2}, {7, 13, -1, -1}, {8, 14, 6, -1},
		{9, 15, 7, 3}, {10, 16, 8, 4}, {11, 17, 9, 5}, {12, 18, 10, -1}, {-1, 19, 11, -1}, {14, 20, -1, 6}, {15, 21, 13, 7}, {16, 22, 14, 8},
		{17, 23, 15, 9}, {18, 24, 16, 10}, {19, 25, 17, 11}, {-1, 26, 18, 12}, {21, -1, -1, 13}, {22, -1, 20, 14}, {23, 27, 21, 15}, {24, 28, 22, 16},
		{25, 29, 23, 17}, {26, -1, 24, 18}, {-1, -1, 25, 19}, {28, 30, -1, 22}, {29, 31, 27, 23}, {-1, 32, 28, 24}, {31, -1, -1, 27}, {32, -1, 30, 28}, {-1, -1, 31, 29}
	};
	/* Distance from hole location to goal (center) */
	public static int[] dist = {4, 3, 4, 3, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1, 0, 1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 3, 4, 3, 4};
	//{3, 3, 3, 2, 2, 2, 3, 2, 1, 1, 1, 2, 3, 3, 2, 1, 0, 1, 2, 3, 3, 2, 1, 1, 1, 2, 3, 2, 2, 2, 3, 3, 3};
	/* actual distance from hole location to goal (center) w/o diagonals */
	public static int[] trueDist = {3, 3, 3, 2, 2, 2, 3, 2, 1, 1, 1, 2, 3, 3, 2, 1, 0, 1, 2, 3, 3, 2, 1, 1, 1, 2, 3, 2, 2, 2, 3, 3, 3};


	public static LinkedList<State>closedList = new LinkedList<State>();
	public static sortState pqs = new sortState(); // sort the priority queue
    public static PriorityQueue<State> openList = new PriorityQueue<State>(2, pqs);
	
	public static State exploreBranch(State currentState) {
		State next;
		
		for(int i=0; i<33; i++) {
			// For each non-empty hole, check its peg for adjacent possibilities.
			if(currentState.getBoard().isOccupied(i) == true) {
				for(int j=0; j<4; j++) {
					// Initialize the state and search the direction.
					next = new State(currentState.getBoard().getList(), currentState.getG(), currentState.getH());
					next.searchDirection(i, j);
					
					if(next.getF() > -1) {
						//System.out.println(next.printBoard());
						//System.out.println(next.printBoard() + "\nPegs: " + next.getBoard().pegCount() + ", F: " + next.getF()  + ", G: " + next.getG()  + ", H: " + next.getH() + "\n----------------");
						
						openList.add(next);
						closedList.add(next);
					}
				}
			}
		}

		// add the current state to the closed list, since it was already looked at.
		closedList.add(currentState);
		if(currentState.getBoard().pegCount() <= 1 && currentState.getBoard().pos(16) == 1) {
			System.out.println("Solution found!");
			System.out.println(currentState.printBoard() + "\nPegs: " + currentState.getBoard().pegCount() + ", F: " + currentState.getF()  + ", G: " + currentState.getG()  + ", H: " + currentState.getH() + "\n----------------");
			System.exit(1);
		}
		
		return currentState;
	}
	
	
	public static void init() {
		System.out.println("A* Search (may take a minute or two)...");
		while(openList.size() > 0) {
			exploreBranch(openList.poll());
		}
	}
	
	
	public static void main(String[] args) {
		closedList.clear();
		openList.clear();
		 int[] init = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		 
		 State initialState = new State(init);
		 closedList.add(initialState);
		 openList.add(initialState);

		init();
	}

}
