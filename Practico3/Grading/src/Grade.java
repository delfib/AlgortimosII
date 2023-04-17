import java.util.Arrays;
import java.util.HashMap;

public class Grade {
    public static void main(String[] args){
        int[] answer = new int[] {3,5,1,2}; 
        System.out.println(gradingPD(answer));
    }

    // Implementa la funcion de grade utilizando porgramacion dinamica
    public static int gradingPD(int[] answer) {
        int[] result = new int[answer.length];
        result = Arrays.copyOf(answer, answer.length);
        Arrays.sort(result);
        return gradingPD(answer, result);
    }

    private static int gradingPD(int[] answer, int[] result){
        // Cantidad de filas = answer.length+1
        int[][] longest = new int[answer.length + 1][result.length + 1];   
        
        // Los casos base no hace falta inicializarlos porque la matriz ya se inicializa con cero y 
        // los casos bases deben ser cero, no los tocamos mas
        
        for(int i = 1; i <= answer.length; i++){
            for(int j = 1; j <= result.length; j++){
                longest[i][j] = Math.max(longest[i-1][j], longest[i][j-1]) + ((answer[i-1] == result[j-1])?1:0);
            }
        }
        return longest[answer.length][result.length];
    }
}
