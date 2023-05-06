package engine;

import java.util.LinkedList;
import java.util.List;

import conventionalsearch.Engine;
import conventionalsearch.StateProblem;
import informedsearch.StateInformed;

/**
 * This class SteepestHillClimbingEngine implements a Steepest Hill Climbing search strategy which can be used 
 * with any instance of StateInformed.
 */
public class SteepestHillClimbingEngine <S extends StateInformed, P extends StateProblem<S>> implements Engine<S,P> {
    private P sp; // Internal representation of the StateProblem.
    private List<S> path; // path stores the path to the goal.
    private List<S> visitedStates = new LinkedList<S>();
    
    // Constructor
    public SteepestHillClimbingEngine(){
        sp = null;
        path = new LinkedList<S>();
    }

    // Another Constructor
    public SteepestHillClimbingEngine(P sp){
        this.sp = sp;
        path = new LinkedList<S>();
    }

    /** 
    * Starts the search for successful states, following a steepest hill climbing strategy.
    * @return true iff a successful state is found.
    * @pre. problem!=null.
    * @post. the search is performed, the path in list path, and true is returned iff a successful state is found.
    */
    public S performSearch(){
        S current = sp.initialState();
        S goal = null;
        boolean found = false;
        while (!found) {
            visitedStates.add(current);
            if (current.isSuccess()){
                goal = current;
                found = true;
            } else {
                List<S> aux = sp.getSuccessors(current);
                List<S> succs = new LinkedList<S>();  // lista de los sucesores no visitados
                for (int i = 0; i < aux.size(); i++){   // agrego en succs los sucesores no visitados
                    if (!visitedStates.contains(aux.get(i))){
                        succs.add(aux.get(i));
                    }
                }
                current = getBetterBestThan(current, succs);
                if (current == null){
                    break;
                }
            }
        }
        if (goal != null){
            S s = goal;
            while (s != null){
                path.add(0, s);
                s = (S) s.getParent();
            }
        }
        return goal;
    }

    /**
     * Private method that given a list of states, returns the best state that's better than the current one.
     * Out of all the children of the states that are better than the state, it returns the best of the children.
     * Returns the best state that has a better valoration that the current.
     * In this case, we use the operator < to compare the valoration with both states bc we want to minimize this
     * valoration. If we wanted to maximize it, we should be using >.
     */
    public S getBetterBestThan(S current, List<S> succs){
        S bestChild = succs.get(0);
        for (int i = 1; i < succs.size(); i++){
            if (succs.get(i).value() <= current.value() && succs.get(i).value() < bestChild.value()){
                bestChild = succs.get(i);
            }
        }
        
        if (bestChild.value() <= current.value()){
            return bestChild;
        } else {
            return null;
        }
    }
    

    public List<S> getPath(){
        return path;
    }


    public void report(){
        System.out.println("Length of path to state when search finished: " + path.size());
    }
}
