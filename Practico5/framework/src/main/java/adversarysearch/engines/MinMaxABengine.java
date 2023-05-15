package adversarysearch.engines;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class MinMaxABengine <P extends StateProblemAdversary<S>, S extends StateAdversary> implements EngineAdversary<P,S> {
    private P sp;
    private int maxDepth;

    /**
     * {@inheritDoc}}
     */
    @Override
    public int getMaxDepth() {
        return maxDepth;    
    }

    @Override
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public P getProblem() {
        return sp;
    }

    @Override
    public void setProblem(P p) {
        this.sp = p;
    }

    /**
     * {@inheritDoc}}
     * This is the MinMax con poda Alfa-Beta algorithm
     */
    @Override
    public int computeValue(S state) {
        return state.value();
    }
    
    // TODO: controlar la depth
    private int minMaxAB(S state, int depth, int alfa, int beta){
        if (state.end() && depth == 0){
            return computeValue(state);
        }
        List<S> succs = sp.getSuccessors(state);
        for (int i = 0; i < succs.size() && alfa < beta; i++){
            if (state.isMax()){
                alfa = Math.max(alfa, minMaxAB(succs.get(i), depth - 1, alfa, beta));
            }
            else {
                beta = Math.min(beta, minMaxAB(succs.get(i), depth - 1, alfa, beta));
            }
        }
        if (state.isMax()){
            return alfa;
        } 
        else {
            return beta;
        }
    }

    public S computeSuccessor(S state) {
        S bestSuccessor = null;
        if (state.isMax()){
            int bestValue = Integer.MIN_VALUE;
            List<S> succs = sp.getSuccessors(state);
            for (S successor : succs){
                int value = minMaxAB(successor, maxDepth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if (value > bestValue){
                    bestValue = value;
                    bestSuccessor = successor;
                }
            }
        }
        else {
            int bestValue = Integer.MAX_VALUE;
            List<S> succs = sp.getSuccessors(state);
            for (S successor : succs){
                int value = minMaxAB(successor, maxDepth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
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