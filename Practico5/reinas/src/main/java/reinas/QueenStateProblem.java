package reinas;

import java.util.LinkedList;
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

    // TODO
    @Override
    public List<QueenState> getSuccessors(QueenState queen){
        throw new UnsupportedOperationException("Implement");
        /*List<QueenState> succs = new LinkedList<QueenState>();
        // we move queen 0
        int[] state = new int[8];

        
        //para cada indice del arreglo, mover hacia la derecha, izquierda, arriba y abajo (4 nuevos estados se forman)
         
        for (int i = 0; i < queen.getQueens().length; i++){
            
        }
        succs.add(new QueenState(state));*/
    }
}
