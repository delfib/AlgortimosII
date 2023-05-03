package engine;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import conventionalsearch.Engine;
import conventionalsearch.StateProblem;
import informedsearch.StateInformed;

/**
 * This class BestFirstEngine implements a Best-first search strategy which can be used 
 * with any instance of StateInformed.
 */
public class BestFirstEngine <S extends StateInformed, P extends StateProblem<S>> implements Engine<S,P> {
    private P sp; // Internal representation of the StateProblem.
    private List<S> path; // path stores the path to the goal.

    // Constructor for BestFirstEngine
    public BestFirstEngine(){
        path = new LinkedList<S>();
    }

    // Another Constructor for BestFirstEngine
    public BestFirstEngine(P sp){
        this.sp = sp;
        path = new LinkedList<S>();
    }

    /** 
    * Starts the search for successful states, following a best-first strategy.
    * @return true iff a successful state is found.
    * @pre. problem!=null.
    * @post. the search is performed, the path in list path, and true is returned iff a successful state is found.
    */
    public S performSearch(){
        PriorityQueue<S> queue;
    }


    public List<S> getPath(){
        return path;
    }


    public void report(){
        System.out.println("Length of path to state when search finished: " + path.size());
    }
}
