package engine;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

/* 
 * Class DepthFirstEngine implements a Depth-first search 
 * strategy which can be used with any instance of StateProblem.
 */
public class DepthFirstEngine <S extends State, P extends StateProblem<S>> implements Engine<S,P> {
    
    private P sp; // internal representation of the StateProblem.
    private List<S> path; // path stores the path to the goal.
    private List<S> visitedStates = new LinkedList<S>();  // this list contains all the states that were visited 

    // Constructor for DepthFirstEngine
    public DepthFirstEngine(){
        path = new LinkedList<S>();
    }

    // Another constructor for DepthFirstSearch
    public DepthFirstEngine(P sp){
        this.sp = sp;
        path = new LinkedList<S>();
    }

    /** 
    * Starts the search for successful states, following a depth-first strategy.
    * @return true iff a successful state is found.
    * @pre. problem!=null.
    * @post. the search is performed, the path in list path, and true is returned iff a successful state is found.
    */
    public S performSearch(){
        Stack<S> stack = new Stack<S>();
        stack.push(sp.initialState());
        boolean found = false;
        S goal = null;
        while (!stack.isEmpty() && !found){
            S current = stack.pop();
            visitedStates.add(current);
            if (current.isSuccess()){
                found = true;
                goal = current;
            }
            else {
                List<S> succs = sp.getSuccessors(current);
                for (S s: succs){
                    if (!visitedStates.contains(s)){
                        stack.push(s);
                    }
                }
            }
        }
        // Let's create the path of this successful state
        if (goal != null){
            S s = goal;
            while (s != null){
                path.add(0,s);
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
