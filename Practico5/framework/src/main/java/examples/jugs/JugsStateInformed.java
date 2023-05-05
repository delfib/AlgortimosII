package examples.jugs;

import informedsearch.StateInformed;

public class JugsStateInformed implements StateInformed {

    private JugsStateInformed parent = null;
    private int contentsA; // contents of jug A
    private int contentsB; // contents of jug B
    
    public boolean repOK() {
        return (contentsA >= 0 && contentsA <= 4 && contentsB >= 0 && contentsB <= 3);
    }

    /** 
     * Constructor for JugsStateInformed class. It initializes the contents of the 
     * jugs with the provided parameters.
     * @param valueA is the integer value used to set the initial contents of the first jug (A).
     * @param valueB is the integer value used to set the initial contents of the second jug (B).
     * @pre. 0<=valueA<=4 and 0<=valueB<=3
     * @post. contentsA is set to valueA, and contentsB is set to valueB.
     */
    public JugsStateInformed(int valueA, int valueB)  {
        this.contentsA = valueA;
        this.contentsB = valueB;
        if (!repOK()) {
            throw new IllegalArgumentException("invalid values");
        }
    }
  
    /** Constructor for JugsStateInformed class. It initializes the contents of the jugs with the provided parameters.
     * @param valueA is the integer value used to set the initial contents of the first jug (A).
     * @param valueB is the integer value used to set the initial contents of the second jug (B).
     * @param parent is the parent state of this.
     * @pre. 0<=valueA<=4 and 0<=valueB<=3
     * @post. contentsA is set to valueA, and contentsB is set to valueB.
    */
    public JugsStateInformed(int valueA, int valueB, JugsStateInformed parent)  {
        this.contentsA = valueA;
        this.contentsB = valueB;
        this.parent = parent;
        if (!repOK()) {
            throw new IllegalArgumentException("invalid values");
        }
    }

    /**
     * Get the contents of Jug A.
     */
    public int getContentsA() {
        return contentsA;
    }

    /**
     * Set the contents of Jug A.
     */
    public void setContentsA(int contentsA) {
        this.contentsA = contentsA;
    }

    /**
     * Get the contents of Jug B.
     */
    public int getContentsB() {
        return contentsB;
    }

    /**
     * Set the contents of Jug B.
     */
    public void setContentsB(int contentsB) {
        this.contentsB = contentsB;
    }
    
    /** 
     * Returns the parent of the current state.
     * @return the parent of the current state or null if this does not have a parent.
     */
    @Override
    public StateInformed getParent() {
        return parent;
    }

    /** 
     * Indicates whether a given state is a successful state, in the context of
     * the current problem. Concrete implementations of AbstractSearchProblem 
     * must implement this routine, to indicate when the search has been successful.
     * @return true iff s is a successful state.
    */
    public boolean isSuccess() {
        return (this.contentsA == 2);
    }

    /** 
     * Checks whether 'this' is equal to another state. This must be implemented
     * by every concrete class implementing State.
     * @param other State object to compare with this.
     * @return true iff 'this' is equal, as a state, to 'other'.
     */
    @Override
    public  boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof JugsStateInformed)) {
            return false;
        }

        JugsStateInformed otherOne = (JugsStateInformed) other;

        return otherOne.contentsA == this.contentsA && otherOne.contentsB == this.contentsB;
    }

    /** 
     * Returns a representation as a string of the current state. This method
     * must be implemented by all concrete classes implementing State.
     * @return a String representation of the current state.
     */
    @Override
    public  String toString() {
        return " (" + this.contentsA + "," + this.contentsB + ") "; 
    }

    // Funcion heuristica / de valoracion
    public int value(){
        return Math.abs(contentsA - 2);
    }
}
