package reinas;

import conventionalsearch.State;

/**
 */
public class QueenState implements State {
    // array to store the positions of the queens in the board. 
    private int[] queens;   // index is the row and value is the column where the queen is in that file
    private QueenState parent = null;   // parent of queens

    // Another Constructor
    public QueenState(int[] queens){
        this.queens = queens;
    }

    // Getter for the queens
    public int[] getQueens(){
        return queens;
    }

    // Getter for the parent
    public QueenState getParent(){
        return parent;
    }

    /**
     * Indicates whether the current state is a successful state 
     */
    public boolean isSuccess(){
        for (int i = 0; i < queens.length; i++) {
            for (int j = i+1; j < queens.length; j++) {
                // check if there's no queens in the same row or column as queen i
                if (i == j || queens[i] == queens[j]) {
                    return false;
                }

                // check if there's no queens in the same diagonal as queen i
                if (Math.abs(queens[i] - queens[j]) == Math.abs(i - j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** 
     * Checks whether 'this' is equal to another state. T
     * @param other State object to compare with this.
     * @return true iff 'this' is equal, as a state, to 'other'.
     */
    @Override
    public  boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof QueenState)) {
            return false;
        }

        QueenState otherOne = (QueenState) other;

        return otherOne.queens == this.queens;
    }

    /** 
     * Returns a representation as a string of the current state. 
     * @return a String representation of the current state.
     * each queen is a pair with its position in the board: (row, column)
     */
    @Override
    public String toString() {
        String result = " [";
        for (int i = 0; i < queens.length; i++){
            result += "(" + i + "," + queens[i] + ")";
        }
        return result; 
    }
}
