package adversarysearch.examples;

import adversarysearch.StateAdversary;
import conventionalsearch.State;

public class TicTacToeState implements StateAdversary {
    private TicTacToeState parent;
    private boolean player; // represents the current player, if its true, then is max, if its false its min
    private int[][] board;

    // Constructor
    public TicTacToeState(boolean player){
        this.player = player;
        board = null;
        parent = null;
    }

    // Another Constructor
    public TicTacToeState(boolean player, TicTacToeState parent, int[][] board){
        this.player = player;
        this.parent = parent;
        this.board = board;
    }

    @Override
    public State getParent() {
       return parent;
    }

    @Override
    public boolean isSuccess() {
        throw new UnsupportedOperationException("Unimplemented method 'isSuccess'");
    }

    @Override
    public boolean isMax() {
        return player;
    }

    @Override
    public boolean end() {
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }

    @Override
    public int value() {
        throw new UnsupportedOperationException("Unimplemented method 'value'");
    }

    @Override
    public boolean equals(Object other){
        throw new UnsupportedOperationException("Unimplemented method 'value'");
    }

    @Override
    public String toString(){
        throw new UnsupportedOperationException("Unimplemented method 'value'");
    }

    @Override
    public Object ruleApplied() {
        throw new UnsupportedOperationException("Unimplemented method 'ruleApplied'");
    }

}