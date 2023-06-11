package adversarysearch.engines;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

/**
* This Class implements the Min-Max algorithm which can be used with any instance of StateProblem.
* @author Delfina Buil
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
     */    
    public int computeValue(S state){
        return state.value();
    }    

    
    private int minMax(S state, int depth){
        if (state.end() || depth >= maxDepth) {  // the state is a leaf
            return computeValue(state);
        } else {
            int x = Integer.MIN_VALUE;
            int y = Integer.MAX_VALUE;

            List<S> succs = sp.getSuccessors(state);
            for (int i = 0; i < succs.size(); i++){
                if (state.isMax()){ 
                    x = Math.max(x, minMax(succs.get(i), depth+1));
                }
                else {  
                    y = Math.min(y, minMax(succs.get(i), depth+1));
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
        if (state.end()) {
            return state;
        }
        S bestSuccessor = null;
        List<S> succs = sp.getSuccessors(state);
        if (state.isMax()) {
            int bestValue = Integer.MIN_VALUE;
            for (S successor : succs){
                int value = minMax(successor, 0);
                if (value > bestValue){
                    bestValue = value;
                    bestSuccessor = successor;
                }
            }
        }
        else {
            int bestValue = Integer.MAX_VALUE;
            for (S successor : succs){
                int value = minMax(successor, 0);
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
