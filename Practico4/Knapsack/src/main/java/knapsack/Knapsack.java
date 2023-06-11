package knapsack;

import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase resuelve el problema de la mochila utilizando la tecnica Greedy
 * EJERCICIO 5 PRACTICO 4
 * Este algoritmo no siempre da como resultado una solucion optima. En el siguiente caso se puede ver esto:
 * values[20,15,12,10]  weights[5,2,2,1]    resultado: [20] pero la solucion optima es [15,12,10]
 */
public class Knapsack{
    public static void main(String[] args){
        int[] values = new int[] {12,10,20,15};  // arreglo con los valores de cada item
        int[] weights = new int[] {2,2,3,1};    // arreglo con los pesos de cada item
        // item con valor 12 pesa 2
        int capacity = 5;   // capacidad de la mochila
        
        System.out.println(knapsackGreedy(values, weights, capacity));
    }

    /**
     * Greedy solution for the knapsack problem. 
     * @param values array of items and its values that can fit into the bag
     * @param weights array of weights of each item
     * @param capacity knapsack's capacity
     * @return result (subset of items that fit into the knapsack, these items are represented with their value)
     */
    public static List<Integer> knapsackGreedy(int[] values, int[] weights, int capacity){
        selectionSort(values, weights);
        List<Integer> result = new LinkedList<Integer>();
        return knapsackGreedy(values, weights, capacity, 0, result);
    }

    
    private static List<Integer> knapsackGreedy(int[] values, int[] weights, int capacity, int i, List<Integer> result){
        if (values == null || weights == null || capacity < 0) throw new IllegalArgumentException("Arguments are not valid");
        if (capacity == 0 || i == values.length) return result;

        // puedo agregar ese item a la mochila
        if (capacity - weights[i] >= 0){
            result.add(values[i]);
            knapsackGreedy(values, weights, capacity - weights[i], i + 1, result);
        }
        else {
            knapsackGreedy(values, weights, capacity, i + 1, result);
        }
        return result;
    }


     // Sorts given arrays in descending order
     public static void selectionSort(int[] array, int[] array2) {
    	if (array == null) throw new IllegalArgumentException("array is null, can't sort");
      	//last = indice del ultimo elemento de la parte no ordenada
      	for (int i = 0; i < array.length; i++) {
         	//largest = posicion del elemento mas grande
         	int largest = indexOfLargest(array, array.length, i);
         	swap(array, i, largest);
            swap(array2, i, largest);
      	}
   	}
    
	private static int indexOfLargest(int[] array, int n, int i){
	    int largest = i;
	  	while (i < n){
            if (array[i] > array[largest]){
                largest = i;
            }
            i++;
        }
	  	return largest;
   	}

   	private static void swap(int[] array, int i, int j) {
    	int temp = array[i];
    	array[i] = array[j];
    	array[j] = temp;
	}
}
