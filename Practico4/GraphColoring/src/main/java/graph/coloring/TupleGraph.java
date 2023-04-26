package graph.coloring;

/**
 * This class represents a Tuple which will be used in GraphColoring.java file.
 */

 public class TupleGraph {
    private Integer first;
    private Integer second;

    /**
     * Simple constructor for a TupleGraph
     */
    public TupleGraph(Integer first, Integer second){
        this.first = first;
        this.second = second;
    }

    /**
     * Getter for first
     */
    public Integer getFirst(){
        return first;
    }

    /**
     * Setter for first
     */
    public void setFirst(Integer first){
        this.first = first;
    }

    /**
     * Getter for second
     */
    public Integer getSecond(){
        return second;
    }

    /**
     * Setter for second
     */
    public void setSecond(Integer second){
        this.second = second;
    }

    /**
     * A toString method for the TupleGraph class
     */
    @Override
	public String toString() {
		return ("(" + first.toString() + "," + second.toString() + ")");
	}

    /**
     * An equals method for the TupleGraph class. Implemented because TupleGraph needs to be used as a key in a HashMap
     */
    @Override
  	public boolean equals(Object other) {
	    if (other == null) {
	      return false;
	    } 
	    if (!(other instanceof TupleGraph)) {
	      return false;
	    }
	    TupleGraph actualOther = (TupleGraph) other;
	    boolean equals = actualOther.getFirst().equals(this.first);
	    equals &= actualOther.getSecond().equals(this.second);
	    return equals;    
	}

    /**
     * A hashcode method for the TupleGraph class. Needs to be implemented in order to be used as a key in a HashMap
     */
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}

