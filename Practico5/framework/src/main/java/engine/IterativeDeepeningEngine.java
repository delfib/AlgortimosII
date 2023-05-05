package engine;
import java.util.List;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

// TODO: hay algo mal en la implementacion porque me deberia devolver el menor camino siempre y no lo esta haciendo
public class IterativeDeepeningEngine <S extends State, P extends StateProblem<S>> implements Engine<S,P> {
    
    private P sp; // Internal representation of the StateProblem.
    private List<S> path; // path stores the path to the goal.

    public S performSearch(){
        S goal = null;
        int depth = 1;
        while (goal == null){
            goal = dfsAcotadoPor(depth);
            depth++;
        }

        // Let's create the path of this successful state
        if (goal != null){
            S s = goal;
            while (s != null){
                path.add(0,s);  // agrega siempre a la cabeza
                s = (S) s.getParent();
            }
        }

        return goal;
    }

    /**
     * Private method that excecutes the dfs limited by a depth
     * @param depth
     * @return S successful state
     */
    private S dfsAcotadoPor(int depth){
        if (depth < 1) throw new IllegalArgumentException("Depth is invalid");

        S goal = dfsAcotadoPor(depth, sp.initialState());
        
        return goal;
    }

    private S dfsAcotadoPor(int depth, S current){
        if (depth == 0) {
            return null;
        }
        if (current.isSuccess()) {
            return current;
        }
        List<S> succs = sp.getSuccessors(current);
        for (S s : succs) {
            S result = dfsAcotadoPor(depth - 1, s);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public List<S> getPath(){
        return path;
    }

    public void report(){
        System.out.println("Length of path to state when search finished: " + path.size());
    }
}
