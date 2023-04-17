import java.util.HashMap;
import java.util.Map;

/**
 * Class that given a number of items, its weights and values, and a capacity, finds the highest value of the
*   items that fit into the knapsack.
 */
public class Knapsack {

    public static void main(String[] args){
        int[] items = new int[] {12,10,20,15};
        int[] weights = new int[] {2,2,2,1};
        int capacity = 5;
        System.out.println(knapsackRec(items, weights, capacity));
        System.out.println(knapsackMemo(items, weights, capacity));
        System.out.println(kanpsackPD(items, weights, capacity));
    }

    /**
     * Recursive solution of the knapsack problem. 
     * @param items array of items that can fit into the bag
     * @param weights array of weights of each item
     * @param capacity knapsack's capacity
     * @return result (most valuable subset of items that fit into the knapsack)
     */
    public static int knapsackRec(int[] items, int[] weights, int capacity){
        return knapsackRec(items, 0, weights, capacity);
    }

    /**
     * Private method that resolves the kanpsack problem
     * @param l index of the items array (items[l] is the current item we're treating)
     * @return
     */
    private static int knapsackRec(int[] items, int l, int[] weights, int capacity){
        if (items == null || l == items.length) return 0;
        if (capacity == 0) return 0;

        // el item l no puede entrar en la mochila porque se "revalsa"
        if (capacity - weights[l] < 0){
            return knapsackRec(items, l+1, weights, capacity);
        }
        else {
            // Me fijo que forma tiene mayor valor, si es mejor sin agregarle el item l o agregandoselo 
            return Math.max(knapsackRec(items, l+1, weights, capacity),   // me fijo si es mejor sin agregarselo, trato los demas indices
            items[l] + knapsackRec(items, l+1, weights, capacity-weights[l])); // me fijo si es mejor agregandoselo sumandole su valor items[l] 
        }
    }




    /**
     * Hashmap used in knapsackMemo for representing the cache memory
     * key = l y capacity   value = values of items
     */
    private static Map<String,Integer> cache = new HashMap<String,Integer>();

    /**
     * Knapsack version using Memoization technique
     */
    public static int knapsackMemo(int[] items, int[] weights, int capacity){
        return knapsackMemo(items, 0, weights, capacity);
    }

    private static int knapsackMemo(int[] items, int l, int[] weights, int capacity){
        // utilizamos como key a la capacidad y el indice actual de items con el que estamos tratando
        // si key = capacity unicamente, podriamos tener un caso con la misma capacidad pero distinto item
        // y generaria resultados incorrectos
        String key = l + "," + capacity;
        if (!cache.containsKey(key)){
            if (items == null || l == items.length){
                cache.put(key, 0);
                return cache.get(key);
            }
            if (capacity - weights[l] < 0){
                cache.put(key, knapsackMemo(items, l+1, weights, capacity));
            }
            else {
                cache.put(key, Math.max(knapsackMemo(items, l+1, weights, capacity),
                items[l] + knapsackMemo(items, l+1, weights, capacity-weights[l])));
            }
        }
        return cache.get(key);
    }



    /**
     * Knapsack version using Dinamic Programming
     */
    public static int kanpsackPD(int[] items, int[] weights, int capacity){
        int[][] matrix = new int[items.length+1][capacity+1];
    
        for (int i = 0; i <= items.length; i++){
            for (int j = 0; j < capacity+1; j++){
                if (i == 0 || j == 0){
                    matrix[i][j] = 0;
                }
                else {
                    if ((j - weights[i-1]) < 0){
                        matrix[i][j] = matrix[i-1][j];
                    }
                    else {
                        matrix[i][j] = Math.max(matrix[i-1][j], items[i-1] + matrix[i-1][j- weights[i-1]]);
                    }
                }
            }
        }
        return matrix[items.length][capacity];
    }
}
