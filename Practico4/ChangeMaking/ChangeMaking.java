import java.util.LinkedList;
import java.util.List;

/*
 * This class solves the change making problem. EJERCICIO 1 PRACTICA 4
 */

public class ChangeMaking {
    
    public static void main(String[] args) {
        int[] coins = new int[] {10,5,1,25};    // coins values
        int[] cantCoins = new int[] {5,1,6,3};  // coins quantities of each value
        int change = 63;

        int[] coinss = new int[] {5,1,7};    // coins values
        int[] cantCoinss = new int[] {5,6,4};  // coins quiantities of each value
        int changee = 25;

        System.out.println(changeMaking(change, coins, cantCoins)); // [25,25,10,1,1,1]
        System.out.println(changeMaking(changee, coinss, cantCoinss)); // [7,7,7,1,1,1,1]
    }
    
    public static List<Integer> changeMaking(int change, int[] coins, int[] cantCoins) {
        selectionSort(coins, cantCoins);
        List<Integer> result = new LinkedList<>();
        return changeMaking(change, coins, cantCoins, 0, result);
    }

    private static List<Integer> changeMaking(int change, int[] coins, int[] cantCoins, int i, List<Integer> result) {

        if (coins == null) throw new IllegalArgumentException("There's no coins");
        if (change == 0) return result;
    
        if (cantCoins[i] > 0 && change - coins[i] >= 0){
            cantCoins[i] = cantCoins[i] - 1;    // we have one less coin
            result.add(coins[i]);   
            changeMaking(change - coins[i], coins, cantCoins, i, result);
        }
        else {
            changeMaking(change, coins, cantCoins, i+1, result);
        }
        return result;
    }    

    // Sorts given array in descending order using the SelectionSort algorithm
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
    
	private static int indexOfLargest(int[] array, int n, int i) {
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



