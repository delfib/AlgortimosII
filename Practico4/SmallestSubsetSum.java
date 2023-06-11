import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem: 
 *  Given an array of non-negative integers, the task is to find the 
 *  minimum subset of elements such that their sum should be greater than 
 *  the sum of the rest of the elements of the array.
 * 
 *  Examples:
 *      Input: arr[] = [ 3 , 1 , 7, 1 ]
 *      Output: 1
 *      Explanation: Smallest subset is {7}. 
 */
public class SmallestSubsetSum {
    public static void main(String[] args) {
        int[] array = new int[] {3,1,7,1};
        int[] array1 = new int[] {2,1,2};

        System.out.println(smallestSubsetSum(array));
        System.out.println(smallestSubsetSum(array1));
    }

    /**
     * Given an array of non-negative integers, return the minimum
     * subset of integers such that their sum is greater than 
     * the sum of the rest of the elements.
     * @param array
     * @return minimum subset with its sum greater than the sum of the rest of elem
     */
    public static List<Integer> smallestSubsetSum(int[] array) {
        List<Integer> subset = new LinkedList<Integer>();
        Arrays.sort(array); // Sorted in ascending order
        int totalSum = 0;   // total sum of all the items in the array
        int subsetSum = 0;  // sum of all the elements in the subset
        for (int i = 0; i < array.length; i++) {
            totalSum += array[i];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            totalSum -= array[i];
            subsetSum += array[i];
            subset.add(array[i]);
            if (totalSum < subsetSum) 
                break;
        }
        return subset;
    }
}
