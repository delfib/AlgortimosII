import java.util.Arrays;
import java.util.Collections;

/**
 * Problem: 
 *  In a candy store, there are N different types of candies available 
 *  and the prices of all the N different types of candies are provided. 
 *  There is also an attractive offer by the candy store. We can buy a single
 *  candy from the store and get at most K other candies (all are different types) for free.
 *
 *   Find the minimum amount of money we have to spend to buy all the N different candies.
 *   Find the maximum amount of money we have to spend to buy all the N different candies.
 * 
 *      For more examples visit: https://www.geeksforgeeks.org/find-minimum-maximum-amount-buy-n-candies/
 */

public class BuyAllCandies {
    public static void main(String[] args) {
        int[] candies = new int[] {3,2,1,4};
        System.out.println(minMoneyToBuyAll(candies, 2));
        System.out.println(maxMoneyToBuyAll(candies, 2));
    }

    /**
     * Returns the mimimum amount of money needed to buy all N candies
     * @param candies
     * @param k amount of free candies per candy bought
     * @return minimum amount of money to buy all candies
     */
    public static int minMoneyToBuyAll(int[] candies, int k) {
        int minMoney = 0;
        // Sorted in ascending order
        Arrays.sort(candies);
        int n = candies.length;
        for (int i = 0; i < n; i++) {
            minMoney += candies[i];
            n -= k;
        }
        return minMoney;
    }

    /**
     * Returns the maximum amount of money needed to buy all N candies
     * @param candies
     * @param k amount of free candies per candy bought
     * @return maximum amount of money to buy all candies
     */
    public static int maxMoneyToBuyAll(int[] candies, int k) {
        int maxMoney = 0;
        // Sorted in ascending order
        Arrays.sort(candies);
        int n = 0;
        for (int i = candies.length - 1; i >= n; i--) {
            maxMoney += candies[i];
            n += k;
        }
        return maxMoney;
    }
}
