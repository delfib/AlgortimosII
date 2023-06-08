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
    private int[][] board = new int[3][3];  // 1 == X   2 == O

    // Constructor
    public TicTacToeState(boolean player){
        this.player = player;
        parent = null;
    }
    
    // Another Constructor
    public TicTacToeState(boolean player, int[][] board){
        this.player = player;
        this.board = board;
        parent = null;
    }

    @Override
    public State getParent() {
       return parent;
    }

    public int[][] getBoard(){
        return board;
    }

    public void setBoard(int[][] board){
        this.board = board;
    }

    public void setPlayer(boolean player){
        this.player = player;
    }
    
    @Override
    public boolean isSuccess() {
        // Check if there's a row full of X's
        for (int row = 0; row < 3; row++){
            if (board[row][0] == 1 && board[row][1] == 1 && board[row][2] == 1) return true;
        }

        // Check if there's a column full of X's
        for (int col = 0; col < 3; col++){
            if (board[0][col] == 1 && board[1][col] == 1 && board[2][col] == 1) return true;
        }

        // Check if there's a diagonal full of X's
        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) return true;

        if (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) return true;

        return false;
    }

    /**
     * Metodo utilizado en end().
     * @return true sii el adversario gano
     */
    public boolean adversaryIsSuccess() {
        // Check if there's a row full of X's
        for (int row = 0; row < 3; row++){
            if (board[row][0] == 2 && board[row][1] == 2 && board[row][2] == 2) return true;
        }

        // Check if there's a column full of X's
        for (int col = 0; col < 3; col++){
            if (board[0][col] == 2 && board[1][col] == 2 && board[2][col] == 2) return true;
        }

        // Check if there's a diagonal full of X's
        if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) return true;

        if (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2) return true;

        return false;
    }

    @Override
    public boolean isMax() {
        return player;
    }

    /**
     * Llegamos al final cuando todo el tablero esta lleno, se hicieron todas las jugadas.
     */
    @Override
    public boolean end() {
        if (isSuccess()) return true;
        if (adversaryIsSuccess()) return true;
        boolean isBoardEmpty = true;
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (board[row][col] == 0) {
                    isBoardEmpty = false;
                }
                if (isBoardEmpty == false) break;
            }
        }
        return isBoardEmpty;
    }


    @Override
    public int value() {
        int[][] lines = {
            {0, 1, 2}, // Horizontal lines
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6}, // Vertical lines
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8}, // Diagonal lines
            {2, 4, 6}
        };
    
        int maxScore = 0;
        int minScore = 0;
    
        for (int[] line : lines) {
            int xCount = 0;
            int oCount = 0;
    
            for (int i : line) {
                int row = i / 3; // Obtener la fila correspondiente al índice
                int col = i % 3; // Obtener la columna correspondiente al índice
    
                if (board[row][col] == 1) {
                    xCount++;
                } else if (board[row][col] == 2) {
                    oCount++;
                }
            }
    
            if (xCount == 3) {
                maxScore += 100; // Player X (MAX) has a winning line
            } else if (oCount == 3) {
                minScore -= 100; // Player O (MIN) has a winning line
            } else {
                // Assign smaller weights to incomplete lines
                maxScore += xCount * 10;
                minScore -= oCount * 10;
            }
        }
    
        return maxScore + minScore;
    }
    
    /*public int value() {
        // el estado es exitoso, puede ser final o no. Significa que ganamos
        if (isSuccess()) {
            return 1; 
        } else if (end()) { // el estado es final pero no exitoso, significa que perdio
            return -1; 
        } else {
            return 0; // Estado no terminado ni exitoso
        }
    }*/

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
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Let's play!\n");
    sb.append("\n");
    for (int row = 0; row < 3; row++) {
        for (int col = 0; col < 3; col++) {
            if (board[row][col] == 1) {
                sb.append(" X ");
            } else if (board[row][col] == 2) {
                sb.append(" O ");
            } else {
                sb.append(" _ ");
            }
        }

        sb.append("\n");
    }

    return sb.toString();
}


    // TODO
    @Override
    public Object ruleApplied() {
        throw new UnsupportedOperationException("Unimplemented method 'ruleApplied'");
    }

}