// Java program to find Minimum
// number of jumps to reach end
import java.io.*;
import java.util.*;

public class MinJumps {

	public static void main(String args[]){
		int arr[] = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };
		//System.out.print("Minimum number of jumps to reach end is "	+ minJumps(arr, 0, arr.length - 1));
        System.out.println(minJumps(arr, 0, arr.length - 1));
	}

	// Returns minimum number of jumps to reach arr[h] from arr[l]. Inefficient version
	public static int minJumps(int arr[], int l, int h){
		// Base case: when origen and destination are the same
		if (h == l) return 0;

		// When nothing is reachable from the given origen
		if (arr[l] == 0) return Integer.MAX_VALUE;  // el integer mas grande de java se le asigna

		// Traverse through all the points reachable from arr[l]. Recursively get the minimum number of jumps
		// needed to reach arr[h] from the reachable points.
		int min = Integer.MAX_VALUE;
		for (int i = l + 1; i <= h && i <= l + arr[l]; i++){
			int jumps = minJumps(arr, i, h);
			if (jumps != Integer.MAX_VALUE && jumps + 1 < min){
				min = jumps + 1;
            }
		}
		return min;
	}
}
