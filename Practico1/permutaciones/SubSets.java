/**
 * Clase que calcula todos los subconjuntos de un conjunto dado 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SubSets {
    public static void main(String[] args){
        List<Integer> listA = new ArrayList<>();

        listA.add(1);
        listA.add(2);
        listA.add(3);

        System.out.println("Todos los subconjuntos de " + listA + " = " + subsets(listA));
        System.out.println("Subconjuntos de " + listA + " que sumen n = " + subSetSumN(listA, 4));
    }

    /**
     * Metodo que dado una lista (conjunto), calcula todos su subconjuntos
     */
    public static List<List<Integer>> subsets(List<Integer> nums) {
        List<List<Integer>> list = new ArrayList<>();
        Collections.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }
    
    /**
     * Metodo privado que hace backtracking para calcular todos los subconjuntos 
     */
    private static void backtrack(List<List<Integer>> list , List<Integer> tempList, List<Integer> nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.size(); i++){
            tempList.add(nums.get(i));
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * Metodo que dado un conjunto s y un valor n, decidir si existe un subconjunto de s cuya suma sea n. 
     */
    public static List<List<Integer>> subSetSumN(List<Integer> set, int n){
        List<List<Integer>> subSetsOfSet = new ArrayList<>();
        subSetsOfSet = subsets(set);    // lista que contiene todos los subconjuntos de set
        
        if (set == null && n == 0) return subSetsOfSet; 
        if (set == null && n!=0) throw new IllegalArgumentException("Set is null and n > 0");


        List<List<Integer>> result = new ArrayList<>(); // todos los subconjuntos de set que sumen n
        int sumSubSet = 0;

        for (int i = 0; i < subSetsOfSet.size(); i++){
            for (int j = 0; j < subSetsOfSet.get(i).size(); j++){
                sumSubSet += subSetsOfSet.get(i).get(j);
            }
            if (sumSubSet == n){
                result.add(subSetsOfSet.get(i));
            }
            sumSubSet = 0;  // La reseteamos 
        }
        return result;
    }
}
