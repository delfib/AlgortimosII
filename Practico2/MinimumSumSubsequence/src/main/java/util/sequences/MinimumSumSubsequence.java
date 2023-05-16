package util.sequences;
/**
 * Computeds minimum sum subsequence of Integer sequences
 * TODO: NO ESTA TERMINADA, NO ANDA BIEN
 */

 public class MinimumSumSubsequence{
	
	public static void main(String[] args){

        Integer[] sequence = new Integer[] {2,-10,1,-10,2};	// rta: (-19,1,3)

        System.out.println(minimumSumSubsequence(sequence));
    }

	/**
	* Computes a minimum sum subsequence by divide and conquer strategy.
	* @param sequence is an Integer sequence.
	* @return return a tuple containing,  value of the sum, the begin index
	* of subsequence and the end of the subsequence
	*/
	public static Tuple <Integer,Integer,Integer> minimumSumSubsequence (Integer[] sequence){
		if (sequence == null) throw new IllegalArgumentException("sequence is null");

		Tuple <Integer,Integer,Integer> sumMin = new Tuple<>(0,-1,-1);

		Tuple <Integer,Integer,Integer> resultLeft = new Tuple<>(0,-1,-1);
		Tuple <Integer,Integer,Integer> resultRight = new Tuple<>(0,-1,-1);
		Tuple <Integer,Integer,Integer> resultMiddle = new Tuple<>(0,-1,-1);

		if (sequence.length <= 1){
			return sumMin;
		}

		int mid = sequence.length / 2;
        Integer[] mitIzq = new Integer[mid];
        Integer[] mitDer = new Integer[sequence.length - mid];

        for (int i = 0; i < mid; i++){
            mitIzq[i] = sequence[i];
        }

        for (int i = mid; i < sequence.length; i++){
            mitDer[i - mid] = sequence[i];
        }

		int sumaParcial = 0;

		for (int i = 0; i < mitIzq.length; i++){
			//sumaParcial += mitIzq[i];
			if (resultLeft.getFirst() > sumaParcial + mitIzq[i]){
				resultLeft.setFirst(sumaParcial + mitIzq[i]);
				sumaParcial += mitIzq[i];
				if (resultLeft.getSecond() == -1){
					resultLeft.setSecond(i);
				}
				resultLeft.setThird(i);
			}
		}

		sumaParcial = 0; 

		for (int j = 0; j < mitDer.length; j++){
			//sumaParcial += mitDer[j];
			if (resultRight.getFirst() > sumaParcial + mitDer[j]){
				resultRight.setFirst(sumaParcial + mitDer[j]);
				sumaParcial += mitDer[j];
				if (resultRight.getSecond() == -1){
					resultRight.setSecond(j);
				}
				resultRight.setThird(j);
			}
		}


        // Split
    	resultLeft = minimumSumSubsequence(mitIzq);
        resultRight = minimumSumSubsequence(mitDer);
		resultMiddle = middleSumSubsequence(sequence);


		sumMin = merge(resultLeft,resultRight,resultMiddle);

		return sumMin;
	}

	private static Tuple <Integer,Integer,Integer> middleSumSubsequence(Integer[] array){
		int middle = array.length / 2;
		Tuple <Integer,Integer,Integer> result = new Tuple<>(0,middle,middle);
		int i = middle-1, j = middle+1;
		boolean ii = true, jj = true, ij = true;	// true means empty, false means full 

		int sumaParcial = array[middle];
		int sumaParcialI = 0, sumaParcialJ = 0, sumaParcialIJ = 0;
		int secondI = -1, thirdJ = -1, secondIJ = -1, thirdIJ = -1;
		int sumaDeTodoI = 0, sumaDeTodoJ = 0, sumaDeTodoIJ = 0;

		while (j < array.length && i >= 0){
			// Caso i
			if (ii){	// is empty
				if (sumaParcial > sumaParcial + array[i]){
					sumaParcialI += array[i];
					secondI = i;
				}
				else {
					ii = false;
					sumaDeTodoI += array[i];
				}
			}
			else {	// ii not empty
				if (sumaParcial > sumaDeTodoI + array[i]){
					sumaParcialI += (sumaDeTodoI + array[i]);
					sumaDeTodoI = 0;
					ii = true;
					secondI = i;
				}
				else {
					ii = false;
					sumaDeTodoI += array[i];
				}
			}	

			// Caso j
			if (jj){	// is empty
				if (sumaParcial > sumaParcial + array[j]){
					sumaParcialJ += array[j];
					thirdJ = j;
				}
				else {
					jj = false;
					sumaDeTodoJ += array[j];
				}
			}
			else {	// jj not empty
				if (sumaParcial > sumaDeTodoJ + array[j]){
					sumaParcialJ += (sumaDeTodoJ + array[j]);
					sumaDeTodoJ = 0;
					jj = true;
					thirdJ = j;
				}
				else {
					jj = false;
					sumaDeTodoJ += array[j];
				}
			}

			// Caso i y j
			if (ij){	// is empty
				if (sumaParcial > array[i] + array[j]){
					sumaParcialIJ += (array[i] + array[j]);	// antes tenia = en vez de +=
					secondIJ = i;
					thirdIJ = j;
				}
				else {
					ij = false;
					sumaDeTodoIJ = array[i] + array[j];
				}
			}
			else {	// is not empty
				if (sumaParcial > sumaDeTodoIJ + array[i] + array[j]){
					sumaParcialIJ += (sumaDeTodoIJ + array[i] + array[j]);
					ij = true;
					sumaDeTodoIJ = 0;
					secondIJ = i;
					thirdIJ = j;
				}
				else {
					ij = false;
					sumaDeTodoIJ += (array[i] + array[j]);
				}
			}

			i--;
			j++;
		}

		while (i >= 0){
			if (ii){	// is empty
				if (sumaParcial > sumaParcial + array[i]){
					sumaParcialI += array[i];
					secondI = i;
				}
				else {
					ii = false;
					sumaDeTodoI += array[i];
				}
			}
			else {	// ii not empty
				if (sumaParcial > sumaDeTodoI + array[i]){
					sumaParcialI += (sumaDeTodoI + array[i]);
					sumaDeTodoI = 0;
					ii = true;
					secondI = i;
				}
				else {
					ii = false;
					sumaDeTodoI += array[i];
				}
			}
			i--;
		}

		while (j < array.length){
			if (jj){	// is empty
				if (sumaParcial > sumaParcial + array[j]){
					sumaParcialJ += array[j];
					thirdJ = j;
				}
				else {
					jj = false;
					sumaDeTodoJ += array[j];
				}
			}
			else {	// jj not empty
				if (sumaParcial > sumaDeTodoJ + array[j]){
					sumaParcialJ += (sumaDeTodoJ + array[j]);
					sumaDeTodoJ = 0;
					jj = true;
					thirdJ = j;
				}
				else {
					jj = false;
					sumaDeTodoJ += array[j];
				}
			}
			j++;
		}

		if (sumaParcialI <= sumaParcialJ && sumaParcialI <= sumaParcialIJ){
			result.setFirst(sumaParcialI + array[middle]); 
			result.setSecond(secondI);
		}
		if (sumaParcialJ <= sumaParcialI && sumaParcialJ <= sumaParcialIJ){
			result.setFirst(sumaParcialJ + array[middle]);
			result.setThird(thirdJ);
		}
		if (sumaParcialIJ < sumaParcialI && sumaParcialIJ < sumaParcialJ){
			result.setFirst(sumaParcialIJ + array[middle]);
			result.setSecond(secondIJ);
			result.setThird(thirdIJ);
		}
		return result;
	}

	private static Tuple <Integer,Integer,Integer> merge(Tuple <Integer,Integer,Integer> resultLeft, Tuple <Integer,Integer,Integer> resultRight, Tuple <Integer,Integer,Integer> resultMiddle){
		int sumaIzq = resultLeft.getFirst();
		int sumaDer = resultRight.getFirst();
		int sumaMiddle = resultMiddle.getFirst();

		Tuple <Integer,Integer,Integer> sumMin = new Tuple<>(0,-1,-1);

		//  el caso donde se retorna []?
		/*if (sumaIzq + sumaDer > sumaIzq && sumaIzq + sumaDer > sumaDer){
			sumMin.setFirst(sumaIzq + sumaDer); 
			sumMin.setSecond(resultLeft.getSecond());
			sumMin.setThird(resultRight.getThird());
			return sumMin;
		}*/
		if (sumaIzq < sumaDer && sumaIzq < sumaMiddle){
			sumMin = resultLeft;
		}
		else if (sumaDer < sumaIzq && sumaDer < sumaMiddle){
			sumMin = resultRight;
		}
		else if (sumaMiddle < sumaIzq && sumaMiddle < sumaDer){
			sumMin = resultMiddle;
		}

		return sumMin;
	}
}

/**
 * [2,1,-10,1,-10,2]
 */