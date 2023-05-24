package ga;


/**
* Knapsack Problem characterization.
*/
public class KnapsackProblem {
    
    // El elemento 0 tiene valor values[0] y peso weights[0]
    private int maxCapacity;    // capacidad maxima de la mochila
    private int[] values;   // valor de cada elemento que se puede agregar en la mochila
    private int[] weights;  // peso de cada elemento que se puede agregar en la mochila

    /**
     * KnapsackProblem constructor.
     * @param maxCapacity maximum capacity allowed.
     */
    public KnapsackProblem(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Another constructor 
     */
    public KnapsackProblem(int maxCapacity, int[] values, int[] weights){
        this.maxCapacity = maxCapacity;
        this.values = values;
        this.weights = weights;
    }

    /**
     * Getter for the maxCapacity
     */
    public int getMaxCapacity(){
        return maxCapacity;
    }

    /**
     * Getter for the values
     */
    public int[] getValues(){
        return values;
    }

    /**
     * Getter for the weights
     */
    public int[] getWeights(){
        return weights;
    }

}
