package adversarysearch.examples;

import adversarysearch.StateAdversary;
import conventionalsearch.State;

/**
 * Los jugadores seran la maquina y el usuario. La maquina se representa por las X y el
 * usuario por las O.
 */
public class TicTacToeState implements StateAdversary {
    private TicTacToeState parent;  // TODO: creo que no lo necesito
    private boolean player; // represents the current player, if its true, then is max, if its false its min
    private char[][] board = new char[3][3];

    // Constructor
    public TicTacToeState(boolean player){
        this.player = player;
        board = null;
        parent = null;
    }
    
    
    @Override
    public State getParent() {
       return parent;
    }

    @Override
    public boolean isSuccess() {
        // Check if there's a row full of X's
        for (int row = 0; row < 3; row++){
            if (board[row][0] == 'X' && board[row][1] == 'X' && board[row][2] == 'X') return true;
        }

        // Check if there's a column full of X's
        for (int col = 0; col < 3; col++){
            if (board[0][col] == 'X' && board[1][col] == 'X' && board[2][col] == 'X') return true;
        }

        // Check if there's a diagonal full of X's
        if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') return true;

        if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') return true;

        return false;
    }

    @Override
    public boolean isMax() {
        return player;
    }

    @Override
    public boolean end() {
        if (isSuccess()) return true;
        boolean isBoardEmpty = true;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board[row][col] != 'X' && board[row][col] != 'O') {
                    isBoardEmpty = false;
                    break;
                }
            }
        }
        return isBoardEmpty;
    }

    @Override
    public int value() {
        throw new UnsupportedOperationException("Unimplemented method 'value'");
    }

    @Override
    public  boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TicTacToeState)) {
            return false;
        }

        TicTacToeState otherOne = (TicTacToeState) other;

        return otherOne.parent == this.parent && otherOne.player == this.player && otherOne.board == this.board;
    }


    @Override
    public String toString(){
        String str = "";
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                str += board[row][col];
            }
            str += "/n";
        }
        return str;
    }

    // TODO
    @Override
    public Object ruleApplied() {
        throw new UnsupportedOperationException("Unimplemented method 'ruleApplied'");
    }

}