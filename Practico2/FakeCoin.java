/*
 * This class implements a solution for the fake coin problem. 
 * Idea :
 *  Input = an array with coins.length coins and its weight
 *  Output = weight of the fake coin
 *  Keep in mind: The fake coin wegiths less than the real coins.
 *  We recieve the array of coins. If the amount of coins is even, we divide the array into two arrays.
 *  If the amount is odd, we divide the coins array of length - 1 into two arrays en the last coin is set apart.
 *  In case the amount is even, we check if the sum of all the weights of both arrays is the same, then there's no fake coin.
 *  If the leftSum is less than rightSum, the fake coin is on the left array, same with leftSum > rightSum. 
 *  In case the amount is odd, same thing happens when leftSum < rigthSum & leftSum > rigthSum, but if they're equal, then the 
 *  last coin we set apart is the fake one.
 */
import java.util.Arrays;

public class FakeCoin {

    public static void main(String[] args) {
        int[] coins = new int[] {8,8,8,2,8,8,8};    // this is an array of 7 coins. Coin 0 weights 8, fake coin weights 2
        System.out.println(fakeCoin(coins));
    }

    public static int fakeCoin(int[] coins) {
        if (coins.length == 0) {
            throw new IllegalArgumentException("List is empty");
        } 
        else {
            if (coins.length % 2 == 0){ // cantidad par de monedas
                int[] leftCoins = Arrays.copyOfRange(coins, 0, coins.length / 2);
                int[] rightCoins = Arrays.copyOfRange(coins, coins.length / 2, coins.length);
                int leftSum = Arrays.stream(leftCoins).sum();
                int rightSum = Arrays.stream(rightCoins).sum();

                if (leftSum < rightSum) {   // fake coin is in the left side
                    return fakeCoin(leftCoins);
                } 
                else if (leftSum > rightSum) {  // fake coin is in the right side
                    return fakeCoin(rightCoins);
                } 
                else {
                    throw new IllegalArgumentException("No fake coin found");
                }
            }
            else {  // cantidad impar de monedas
                int[] leftCoins = Arrays.copyOfRange(coins, 0, (coins.length - 1) / 2);
                int[] rightCoins = Arrays.copyOfRange(coins, (coins.length - 1) / 2, coins.length - 1);
                int leftSum = Arrays.stream(leftCoins).sum();
                int rightSum = Arrays.stream(rightCoins).sum();
                int impCoin = coins[coins.length-1];

                if (leftSum < rightSum) {   // fake coin is in the left side
                    return fakeCoin(leftCoins);
                } 
                else if (leftSum > rightSum) {  // fake coin is in the right side
                    return fakeCoin(rightCoins);
                } 
                else {  // leftSum == rightSum so the impCoin is the fake one
                    return impCoin;
                }
            }   
        }
    }
}


