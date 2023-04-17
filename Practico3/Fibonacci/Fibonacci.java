import java.util.HashMap;
import java.util.Map;

/**
 * Algoritmo basado en programacion dinamica que calcula el n-esimo numero de Fibonacci
 * -- F0 = 0, F1 = 1, F2 = 1, F3 = 2, F4 = 3, F5 = 5, F6 = 8
 */
public class Fibonacci {
    
    public static void main(String[] args){
        System.out.println(memoFib(6));
    }

    // Version ineficiente recursiva
    public static int fibRec(int n){
        if (n < 0) throw new IllegalArgumentException("undefined for negative integers");
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibRec(n-1) + fibRec(n-2);
    }
    
    // Version eficiente con PD 
    public static int fib(int n){
        if (n == 0) return 0;

        int fibNum1 = 0; 
        int fibNum2 = 1;
        int aux;

        for (int i = 1; i < n; i++){
            aux = fibNum1 + fibNum2;
            fibNum1 = fibNum2;
            fibNum2 = aux;
        }

        return fibNum2;
    }

    // Structure that represents the cache
    private static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    // Version eficiente con Memoization
    public static int memoFib(int n){
        if (n < 0) throw new IllegalArgumentException("undefined for negative integers");

        if (!cache.containsKey(n)){
            if (n == 0){
                cache.put(n,0);
            }
            if (n == 1){
                cache.put(n,1);
            }
            if (n > 1){
                cache.put(n, memoFib(n-1) + memoFib(n-2));
            }
        }
        return cache.get(n);
    }
}
