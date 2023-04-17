import java.util.HashMap;
import java.util.Map;

/**
 * This class solves the subset sum problem: Given a set of non-negative integers, and a value sum, 
 * determine if there is a subset of the given set with sum equal to given sum.
 */
public class SubsetSum {

    public static void main(String[] args) {
        int set[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        System.out.println(isSubsetSum(set, set.length, sum));
        System.out.println(isSubsetSumMemo(set, set.length, sum));
    }
    
    /**
    * Recursive version
    * Returns true if there is a subset of set[] with sum equal to given sum
    */
    public static boolean isSubsetSum(int set[], int n, int sum){
        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
 
        // If last element is greater than sum, then ignore it
        if (set[n - 1] > sum){
          return isSubsetSum(set, n - 1, sum);  
        } 
 
        /* else, check if sum can be obtained
        by any of the following
            (a) including the last element
            (b) excluding the last element */
        else {
            return isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]);
        }
    }
    

    private static Map<String,Boolean> cache = new HashMap<String,Boolean>();

    // Memoization version
    public static boolean isSubsetSumMemo(int set[], int n, int sum){
        String key = (n - 1) + "," + sum;

        // Base Cases
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
 
        if (!cache.containsKey(key)){
            if (set[n - 1] > sum){
                cache.put(key, isSubsetSum(set, n - 1, sum));
            }
            else {
                cache.put(key, isSubsetSum(set, n - 1, sum) || isSubsetSum(set, n - 1, sum - set[n - 1]));
            }
        }
        return cache.get(key);
    }
}
