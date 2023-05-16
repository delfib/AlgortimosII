package adversarysearch.engines;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

/**
* This Class implements the Min-Max algorithm which can be used with any instance of StateProblem.
*/
public class MinMaxEngineAdversary <P extends StateProblemAdversary<S>, S extends StateAdversary> implements EngineAdversary <P,S> {
    private P sp; // Internal representation of the StateProblemAdversary.
    private int maxDepth;   // maximum depth to be used for search

    // Constructor
    public MinMaxEngineAdversary(P sp, int maxDepth){
        this.sp = sp;
        this.maxDepth = maxDepth;
    }

    /**
     * {@inheritDoc}}
     */
    public int getMaxDepth(){
        return maxDepth;
    }

    /**
     * {@inheritDoc}}
     */
    public void setMaxDepth(int maxDepth){
        this.maxDepth = maxDepth;
    }


    public P getProblem(){
        return sp;
    }

    public void setProblem(P sp){
        this.sp = sp;
    }

    /**
     * {@inheritDoc}
     * Este es el algoritmo MinMax
     */    
    public int computeValue(S state){
        return state.value();
    }    

    // TODO: controlar la depth
    private int minMax(S state, int depth){
        if (state.end() || depth == 0) {  // the state is a leaf
            return computeValue(state);
        } else {
            int x = Integer.MAX_VALUE;
            int y = Integer.MIN_VALUE;

            List<S> succs = sp.getSuccessors(state);
            for (int i = 0; i < succs.size(); i++){
                if (state.isMax()){ // state is MAX
                    x = Math.max(x, minMax(succs.get(i), depth - 1));
                }
                else {  // state is MIN
                    y = Math.min(y, minMax(succs.get(i), depth - 1));
                }
            }
            if (state.isMax()){
                return x;
            }
            else {
                return y;
            }
        }
    }

    public S computeSuccessor(S state) {
        S bestSuccessor = null;
        if (state.isMax()){ // state es MAX
            int bestValue = Integer.MIN_VALUE;
            List<S> succs = sp.getSuccessors(state);
            for (S successor : succs){
                int value = minMax(successor, maxDepth - 1);
                if (value > bestValue){
                    bestValue = value;
                    bestSuccessor = successor;
                }
            }
        }
        else {  // state es MIN
            int bestValue = Integer.MAX_VALUE;
            List<S> succs = sp.getSuccessors(state);
            for (S successor : succs){
                int value = minMax(successor, maxDepth - 1);
                if (value < bestValue){
                    bestValue = value;
                    bestSuccessor = successor;
                }
            }
        }

        return bestSuccessor;
    }


    @Override
    public void report() {
        throw new UnsupportedOperationException("Unimplemented method 'report'");
    }
}
