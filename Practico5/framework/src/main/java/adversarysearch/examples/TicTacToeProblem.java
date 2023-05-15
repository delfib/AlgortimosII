package adversarysearch.examples;

import java.util.List;

import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class TicTacToeProblem <S extends StateAdversary> implements StateProblemAdversary <S> {

    @Override
    public S initialState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialState'");
    }

    @Override
    public List<S> getSuccessors(S s) {
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
