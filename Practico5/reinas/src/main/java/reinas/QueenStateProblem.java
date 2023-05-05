package reinas;

import java.util.List;

import conventionalsearch.StateProblem;

public class QueenStateProblem implements StateProblem<QueenState> {
    
    private QueenState initial; // initial state

    // Constructor
    public QueenStateProblem(int[] queens){
        initial = new QueenState(queens);
    }

    @Override
    public QueenState initialState(){
        return initial;
    }

    @Override
    public List<QueenState> getSuccessors(){
        
    }
}
