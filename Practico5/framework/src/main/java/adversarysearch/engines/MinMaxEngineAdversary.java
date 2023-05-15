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
        return computeValue(state, 0);
    }    

    // TODO: falta implementar bien el caso de la depth maxima
    private int computeValue(S state, int depth){
        if (state.end() || depth >= maxDepth) {  // the state is a leaf
            return state.value();
        } else {
            int x = Integer.MAX_VALUE;
            int y = Integer.MIN_VALUE;

            List<S> succs = sp.getSuccessors(state);
            for (int i = 0; i < succs.size(); i++){
                if (state.isMax()){ // state is MAX
                    x = Math.max(x, computeValue(succs.get(i)));
                }
                else {  // state is MIN
                    y = Math.min(y, computeValue(succs.get(i)));
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
        int maxValue = Integer.MIN_VALUE;
        List<S> succs = sp.getSuccessors(state);
        for (S successor : succs){
            if (maxValue < successor.value()){
                maxValue = successor.value();
                bestSuccessor = successor;
            }
        }
        return bestSuccessor;
    }

    @Override
    public void report() {
        throw new UnsupportedOperationException("Unimplemented method 'report'");
    }
}
