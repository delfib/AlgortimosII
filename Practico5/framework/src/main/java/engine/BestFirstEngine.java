package engine;

import java.util.Comparator;
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
    private List<S> visitedStates = new LinkedList<S>();  // this list contains all the states that were visited 

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
    * IMPORTANT!: Hay una posibilidad de caer en un ciclo infinito al estar trabajando con una PriorityQueue, los 
    * estados ya explorados vuelven a ser agregados a la PriorityQueue, esto no pasa en le BreadthFS ya que lo maneja
    * distinto. Es necesario entonces, llevar una cuenta de los estados ya visitados para no volver a agregarlos
    * a la cola de prioridad.
    */
    public S performSearch(){
        PriorityQueue<S> queue = new PriorityQueue<>(Comparator.comparingInt(S::value));
        queue.add(sp.initialState());
        boolean found = false;
        S goal = null;
        while (!queue.isEmpty() && !found){
            S current = queue.poll();
            visitedStates.add(current);
            if (current.isSuccess()){
                found = true;
                goal = current;
            } else {
                List<S> succs = sp.getSuccessors(current);
                for (S s: succs) {
                    if (!visitedStates.contains(s)){
                        queue.add(s);
                    }
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


    public List<S> getPath(){
        return path;
    }


    public void report(){
        System.out.println("Length of path to state when search finished: " + path.size());
    }
}
