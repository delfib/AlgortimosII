package util;

/**
 * Greedy solution to the Policemen catch thieves problem.
 * This problem goes as follows:
 *    Given an array of size n that has the following specifications: 
 *      - Each element in the array contains either a policeman or a thief.
 *      - Each policeman can catch only one thief.
 *      - A policeman cannot catch a thief who is more than K units away from the policeman.
 *    We need to find the maximum number of thieves that can be caught.
 *  Given seqTP =  {'T', 'T', 'P', 'P', 'T', 'P'}, k = 2
 *  3 thieves can be caught. first P can catch first T, second P can catch second T and third P can catch third T
*/

public final class CatchThieves {
     
    /**
    * Returns the maximum number of thieves that can be caught.
    * Each policeman can catch only one thief which is at most k away from him.
    * @param seqTP is the sequence of thieves and policemen, t or p.
    * @param distanceK represent the units away from the policemen to catch a thieve.
    * @return the maximum number of thieves that can be caught.
    */
    public final int maxCatch(final char[] seqTP, final int distanceK) {
        if (seqTP == null || distanceK < 0) throw new IllegalArgumentException("Arguments are invalid");
        if (distanceK == 0) return 0;

        int result = 0; // maximum number of thieves that can be caught
        int currentCop = -1, currentThief = -1; // current indexes of cops and thieves

        // find index of first cop on the left most part of the array
        for (int i = 0; i < seqTP.length; i++){
            if (seqTP[i] == 'P'){
                currentCop = i;  
                break;
            } 
        }

        // find index of first thief on the left most part of the array
        for (int i = 0; i < seqTP.length; i++){
            if (seqTP[i] == 'T') {
                currentThief = i;
                break;
            }
        }


        // if theres no cops or no thieves, no thieves were caught
        if (currentCop == -1 || currentThief == -1) return 0;

        while (currentCop < seqTP.length && currentThief < seqTP.length){
            if (Math.abs(currentCop - currentThief) <= distanceK) { // that cop can catch that thief
                result++;

                // find index of next cop
                currentCop++;
                while (currentCop < seqTP.length && seqTP[currentCop] != 'P'){
                    currentCop++;
                }
                // find index of next thief
                currentThief++;
                while (currentThief < seqTP.length && seqTP[currentThief] != 'P'){
                    currentThief++;
                }
            }
            else {
                // the cop is behind de thief
                if (currentCop < currentThief){
                    currentCop++;
                    while (currentCop < seqTP.length && seqTP[currentCop] != 'P'){
                        currentCop++;
                    }
                }
    
                // the thief is behind the cop
                if (currentThief < currentCop){
                    currentThief++;
                    while (currentThief < seqTP.length && seqTP[currentThief] != 'P'){
                        currentThief++;
                    }
                }
            }
        }
        return result;
    }  
}   
