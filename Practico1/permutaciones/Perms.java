/**
 * Clase que calcula las permutaciones de una lista dada y determina si dos listas son anagramas
 */

import java.util.ArrayList;
import java.util.List;

public class Perms {
    public static void main(String[] args){

        List<Integer> lista = new ArrayList<Integer>();
        lista.add(1);
        lista.add(2);
        lista.add(3);

        List<Integer> listaB = new ArrayList<Integer>();
        listaB.add(1);
        listaB.add(3);
        listaB.add(2);

        System.out.println("Permutaciones de [1,2,3] = " + permutations(lista));
        System.out.println("Son anagramas? = " + sonAnagramas(lista, listaB));  
    }

    /**
     * Metodo que dada una lista de enteros, calcula todas sus permutaciones
     */
    public static List<List<Integer>> permutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();   // Lista donde se alojaran todas las permutaciones
        backtrack(result, new ArrayList<>(), nums);     
        return result;
    }
    
    /**
     * Metodo privado que realiza el backtracking para calcular las permutacioens
     * @param result lista de listas donde se guardaran todas las permutaciones
     * @param tempList lista temporal para ir calculando las permutaciones
     * @param nums lista original
     */
    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, List<Integer> nums){
        if (tempList.size() == nums.size()){    // Tenemos una nueva permutacion 
            result.add(new ArrayList<>(tempList));  // La agregamos a result
        } 
        else{
            for(int i = 0; i < nums.size(); i++){ 
                if (!(tempList.contains(nums.get(i)))) {
                    tempList.add(nums.get(i));  // nums[i] no existe en tempList, lo agregamos
                    backtrack(result, tempList, nums);
                    // Elimina el ultimo elmento de tempList
                    tempList.remove(tempList.size() - 1);
                } 
            }
        }
    } 

    /**
     * Metodo que dadas dos listas retorna True ssi estas son anagramas, es decir, una es permutacion de la otra
     */
    public static boolean sonAnagramas(List<Integer> listaA, List<Integer> listaB){
        List<List<Integer>> permutacionesA = new ArrayList<>();
        permutacionesA = permutations(listaA);

        return permutacionesA.contains(listaB);
    }
}
