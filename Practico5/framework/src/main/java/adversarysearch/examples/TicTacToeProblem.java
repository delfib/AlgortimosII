package adversarysearch.examples;

import java.util.LinkedList;
import java.util.List;


import adversarysearch.StateProblemAdversary;

public class TicTacToeProblem implements StateProblemAdversary <TicTacToeState> {
    private TicTacToeState initial; // the initial state, the starting point of the Adversary Search

    // Constructor
    public TicTacToeProblem(){
        initial = null;
    }

    // Another Constructor
    public TicTacToeProblem(boolean player){
        initial = new TicTacToeState(player);
    }

    @Override
    public TicTacToeState initialState() {
        return initial;
    }

    @Override
    public List<TicTacToeState> getSuccessors(TicTacToeState state) {
        if (state.end()) throw new IllegalArgumentException("State is final, there's no more moves possible");

        List<TicTacToeState> succs = new LinkedList<TicTacToeState>();
        if (state.isMax()){
            int[][] board = state.getBoard();
            for (int row = 0; row < 3; row++){
                for (int col = 0; col < 3; col++){
                    if (board[row][col] == 0) {
                        int[][] newBoard = copyBoard(board);
                        newBoard[row][col] = 1;
                        TicTacToeState newSucc = new TicTacToeState(false, newBoard); // es el turno del adversario pq jugamos nosotros
                        succs.add(newSucc);
                    }
                }
            }
        }
        if (!state.isMax()){
            int[][] board = state.getBoard();
            for (int row = 0; row < 3; row++){
                for (int col = 0; col < 3; col++){
                    if (board[row][col] == 0) {
                        int[][] newBoard = copyBoard(board);
                        newBoard[row][col] = 2;
                        TicTacToeState newSucc = new TicTacToeState(true, newBoard); // es el turno nuestro pq ya jugo el adversario
                        succs.add(newSucc);
                    }
                }
            }
        }
        return succs;
    }

    /**
     * Metodo privado que dado un board, te retorna un board igual
     */
    private int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[3][3];
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                newBoard[row][col] = board[row][col];
            }
        }
        return newBoard;
    }

    @Override
    public int minValue() {
        return -1;
    }

    @Override
    public int maxValue() {
        return 1;
    }
   
}
