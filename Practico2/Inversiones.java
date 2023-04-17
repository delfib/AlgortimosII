/**
    Dado un arreglo A[0..n-1] de nros reales distintos, llamamos inversion a un par de valores
    (A[i],A[j]) almacenados en el arreglo tales que A[i] > A[j], con i < j. 
    Este algoritmo calcula el nro de inversiones en un arreglo dado utilizando Divide & Conquer.
    Resuelto con el MergeSort y una modificacion en este.
*/
public class Inversiones {

    public static void main(String[] args){
		int[] array = {1,20,7,4,5,6};   // 7

        System.out.println(inversiones(array));
		//System.out.println(mergeSortAndCount(array, 0, array.length - 1));
	}

    public static int inversiones(int[] array){
        if (array == null) throw new IllegalArgumentException("Array is null");

        int count = 0;

        if (array.length < 2){
            return count;
        }

        int mid = array.length / 2;

        int[] mitIzq = new int[mid];
        int[] mitDer = new int[array.length - mid];

        for (int i = 0; i < mid; i++) {
        	mitIzq[i] = array[i];
		}
			
        for (int i = mid; i < array.length; i++) {
        	mitDer[i - mid] = array[i];
        }
        
        count += inversiones(mitIzq);
        count += inversiones(mitDer);

        count += merge(mitIzq, mitDer, array);

        return count;
    }

    private static int merge(int[] mitIzq, int[] mitDer, int[] array){
        int i = 0, j = 0, k = 0, count = 0;

        while (i < mitIzq.length && j < mitDer.length){
            if (mitIzq[i] > mitDer[j]){
                if (i + 1 < mitIzq.length){
                    count += 1 * (mitIzq.length - i);
                }
                else {
                    count++;
                }
                array[k] = mitDer[j];
                k++;
                j++;
            }
            else {
                array[k] = mitIzq[i];
                k++;
                i++;
            }
        }

        while (i < mitIzq.length){
            array[k] = mitIzq[i];
            k++;
            i++;
        }

        while (j < mitDer.length){
            array[k] = mitDer[j];
            k++;
            j++;
        }

        return count;
    }
}