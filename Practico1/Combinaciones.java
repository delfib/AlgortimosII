import java.util.ArrayList;
import java.util.List;

public class Combinaciones {

    public static void main(String[] args) {
        int[] numeros = {1,2,3};
        int k = 2; // número de elementos en cada combinación
        List<int[]> result = new ArrayList<>();
        
        List<int[]> combinaciones = new ArrayList<>();

        combinaciones = generarCombinaciones(numeros, k, new int[k], 0, result);

        System.out.println(combinaciones.size());
        for (int i = 0; i < combinaciones.size(); i++){
            for (int j = 0; j < combinaciones.get(i).length; j++) {
                System.out.print(combinaciones.get(i)[j] + " ");
            }
        }
    }

    public static List<int[]> generarCombinaciones(int[] numeros, int k, int[] combinacion, int indice, List<int[]> result) {
        if (indice == k) {
            // se encontró una combinación, guardarla en result
            int[] combi = new int[2];
            combi[0] = combinacion[0];
            combi[1] = combinacion[1];
            result.add(combi);
        } else {
            // explorar ramas del árbol
            for (int i = 0; i < numeros.length; i++) {
                combinacion[indice] = numeros[i];
                generarCombinaciones(numeros, k, combinacion, indice + 1, result);
            }
        }
        return result;
    }
}