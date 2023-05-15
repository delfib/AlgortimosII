package adversarysearch.examples;

import java.util.List;

import adversarysearch.StateAdversary;
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
    public List<TicTacToeState> getSuccessors(TicTacToeState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSuccessors'");
    }

    @Override
    public int minValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'minValue'");
    }

    @Override
    public int maxValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxValue'");
    }
   
}
