package util.math;

import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

/**
 * Computeds greatest common divisor of two nonnegative, not-both-zero
 * integers using diferents algorithms.
 */

public class GreatestCommonDivisor {

	/**
	* Computes greatest common divisor by Euclid's algorithm
	* @param m is a nonnegative integer first argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int euclidAlgorithm(int m, int n){
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		if(n == 0)
			return m;
		return euclidAlgorithm(n,m%n);
	}

	/**
	* Computes greatest common divisor by definition based algorithm
	* @param m is a nonnegative integer first argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	* In this algorithm both m and n must be different from zero on its own.
	*/
	public static int definitionBasedAlgorithm(int m, int n){
		if (m < 0 || n < 0 || m == 0 || n == 0) throw new IllegalArgumentException("numbers must be nonnegative and not zero on its own");
		int r = min(m,n);
		while (m%r != 0 || n%r != 0){
			r--;
		}
		return r;
	}

	/**
	 * Private method that given two numbers, returns the minimum of them
	 */
	private static int min(int m, int n){
		if (m < n) return m;
		else return n;
	}

	/**
	* Computes greatest common divisor by middle school procedure
	* @param m is a nonnegative integer first argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int middleSchoolAlgorithm(int m, int n){
		if (m < 0 || n < 0 || m == 0 || n == 0) throw new IllegalArgumentException("numbers must be nonnegative and not zero on its own");
		int[] primesOfm = new int[m];
		int[] primesOfn = new int[n];
		primesOfm = sieve(m);
		primesOfn = sieve(n);
		return multiplicacion_comun(primesOfm, primesOfn);
	}

	/**
	* Implements the sieve of Eratosthenes
	* @param n is a number greater than 1
	* @return Array of all prime numbers less than or equal to n.
	*/
	private static int[] sieve(int n){
		if (n == 0 || n == 1) throw new IllegalArgumentException("number must be grater than 1");
		int[] array = new int[n];
		for (int i = 2; i < n; i++){
			array[i] = i;
		}
		for (int i = 2; i < Math.floor(Math.sqrt(n)); i++){
			int j;
			if (array[i] != 0){	// i hasn't been eliminated in previous passes. 
				j = i * i;
				while (j < n || j == n){
					array[j] = 0;	// marked as eliminated.
					j = j + i;
				}
			}
		}
		// copy the remaining elements of A to array L of the primes
		int k = 0;
		int[] arrayPrimes = new int[n];
		for (int i = 2; i < n; i++){
			if (array[i] != 0){
				arrayPrimes[k] = array[i];
				k++;
			}
		}
		return arrayPrimes;
	}


	/**
	 * private method which calculates the multiplication of all the 
	 * numers two arrays have in common.
	 */
	public static int multiplicacion_comun(int[] m, int[] n) {
		Map<Integer, Integer> mapM = new HashMap<>();	// en estos hashmaps utilizamos la clave num que dentro de esta se guarda la cantidad de veces que num aparece
		Map<Integer, Integer> mapN = new HashMap<>();
		for (int num : m) {	// para todos los numeros del arreglo m llamados num
			int count = mapM.getOrDefault(num, 0);	// buscamos si ese num esta en el mapM, si no lo esta, obtenemos 0.
			mapM.put(num, count + 1);	// a ese num lo agregamos a mapM e incrementamos la cantidad de ese num por 1 al estar agregando uno mas
		}
		for (int num : n) {
			int count = mapN.getOrDefault(num, 0);
			mapN.put(num, count + 1);
		}

		int result = 1;
		for (int num : mapM.keySet()) {	// para cada num que se encuentre en mapM
			if (mapN.containsKey(num)) {	// nos fijamos si esta contenido en mapN
				int vecesM = mapM.get(num);	// accedemos a la informacion guardad en la clave num, que es la cant de veces que aparece num 
				int vecesN = mapN.get(num);
				int minVeces = Math.min(vecesM, vecesN);
				result *= Math.pow(num, minVeces);	// multiplicamos num una cantidad minVeces
			}
		}
	
		return result;
	}
}