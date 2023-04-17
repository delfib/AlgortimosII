import java.util.HashMap;
import java.util.Map;

/**
 * Algoritmo que calcula el numero combinatorio (n) usando la tecnica de Programacion Dinamica
 *                                              (m)
 */
public class Combinatoria {
    public static void main(String[] args){
        System.out.println(combInef(8, 5));
        System.out.println(combPD(8,5));
        System.out.println(combMemo(8,5));
    }

    /**
     * Version ineficiente de manera recursiva
     */
    public static int combInef(int n, int m){
        if (m == 0 || m == n) return 1;
        if (m > n) return 0;

        return combInef(n-1, m-1) + combInef(n-1, m);
    }

    /**
     * Version eficiente usando PD
     */
    public static int combPD(int n, int m){
        int[][] comb = new int[n+1][m+1];

        // Llenamos la matriz con todos sus calculos
        int i = 0, j = 0;
        for (i = 0; i < n+1; i++){
            //if (i > j) break;
            for (j = 0; j < m+1; j++){
                if (j == 0 || i == j){
                    comb[i][j] = 1;
                }
                else if (j > i){
                    comb[i][j] = 0;
                }
                else {
                    comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
                }
            }
        }
        return comb[n][m];        
    }


    private static Map<Tuple, Integer> cache = new HashMap<Tuple, Integer>();

    /**
     * Memoization version (efficient)
     */
    public static int combMemo(Integer n, Integer m){
        Tuple key = new Tuple(n,m);
        if (!cache.containsKey(key)){
            if (m == 0 || m == n){
                cache.put(key, 1);
            }
            else if (m > n){
                cache.put(key, 0);
            }
            else {
                cache.put(key, combMemo(n-1, m-1) + combMemo(n-1, m));
            }
        }
        return cache.get(key);
    }
}
