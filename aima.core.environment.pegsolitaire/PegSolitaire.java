package aima.core.environment.pegsolitaire;

/**
 * @author Joshua Shor
 * @author Ravi Mohan
 * 
 */
public class PegSolitaire {
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
}
