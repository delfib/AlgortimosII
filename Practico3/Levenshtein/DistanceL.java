import java.util.HashMap;
import java.util.Map;

/**
 * Programa que calcula la distancia Levenshtein de manera eficiente con Programacion Dinamica
 */
public class DistanceL {
    public static void main(String[] args){
        String str1 = new String("hola");
        String str2 = new String("rola");
        System.out.println(levenshteinDistDP(str1, str2));
        System.out.println(levDistMemo(str1, str2));
    }

    /** 
     * Levenshtein Distance implemented with Dinamic Programming
     */
    public static int levenshteinDistDP(String str1, String str2) {

        int[][] distance = new int[str1.length()+1][];

        for(int i = 0; i <= str1.length(); i++){
            distance[i] = new int[str2.length()+1];
            distance[i][0] = i;
        }

        for(int j = 0; j < str2.length() + 1; j++)
            distance[0][j]=j;
        
        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                distance[i][j] = Math.min(Math.min(distance[i-1][j] + 1, distance[i][j-1] + 1), distance[i-1][j-1] + ((str1.charAt(i-1) == str2.charAt(j-1))?0:1));
            }
        }
        return distance[str1.length()][str2.length()];
    }

    
    private static Map<TupleD, Integer> cache = new HashMap<>();

    /** 
     * Levenshtein Distance implemented with Memoization technique
     */
    public static int levDistMemo(String str1, String str2){
        TupleD key = new TupleD(str1.length(), str2.length());
        if (!cache.containsKey(key)){
            if (str2.length() == 0){
                cache.put(key, str1.length());
            }
            else if (str1.length() == 0){
                cache.put(key, str2.length());
            }
            else if (str1.charAt(0) == str2.charAt(0)){
                int g = Math.min(levDistMemo(str1.substring(1, str1.length()), str2.substring(1, str2.length())), 
                Math.min(levDistMemo(str1, str2.substring(1, str2.length()) + 1), 
                levDistMemo(str1.substring(1, str1.length()), str2)+1));
                cache.put(key, g);
            }
            else {
                int g = Math.min(levDistMemo(str1.substring(1, str1.length()), str2.substring(1, str2.length()))+1, 
                Math.min(levDistMemo(str1, str2.substring(1, str2.length()))+1, 
                levDistMemo(str1.substring(1, str1.length()), str2)+1));
                cache.put(key, g);
            }
        }
        return cache.get(key);
    }
}
