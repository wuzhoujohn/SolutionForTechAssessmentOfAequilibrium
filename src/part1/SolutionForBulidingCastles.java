package part1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhou
 *
 */


public class SolutionForBulidingCastles {

	public int CountPeaksAndValleys(int[] input) {
		// to count the peaks and valleys
				int count = 0;
				//set three integers to hold preceding cureent and following numbers
				int preceding;
				int current;
				int following;
				//if the input list is null or it is an empty then return 0	
				if(input.length == 0 || input == null) {
					return 0;
				}
				//since we always bulid castle at the first position, we can skip the first position
				count++;
				preceding = input[0];
				for(int i = 1; i < input.length; i++){	
					current = input[i];
					//if this is the last position
					if(i == input.length - 1){
						//if current is not equal to preceding, then the last position is either a peak or valley
						//we increase the count
						if(current != preceding){
							count++;
						}
						//if current is equal to preceding
						else{
							//do nothing
						}
					}else{
						following = input[i+1];
						//if preceding is not equal to current, current is not equal to following
						if(preceding != current && current != following){
							//decide if this is a peak
							if(current > preceding && current > following){
								count++;
							}
							//decide if this is a valley
							if(current < preceding && current < following){
								count++;
							}
							preceding = current;
						}
						//if preceding equals current, current equals following or 
						//if preceding equals current and current not equals following
						if((preceding == current && current == following) ||
							(preceding == current && current != following)){
							//set preceding to following
							preceding = current;
						}
						//if preceding not equals to current and current equals to following
						if(preceding != current && current == following){
							//do nothing
						}
					}
				}
				return count;
		
	}


	public static void main(String args[]) {
		SolutionForBulidingCastles sfbc = new SolutionForBulidingCastles();
		int[] input = new int[10];
		input[0] = 6;
		input[1] = 1;
		input[2] = 6;
		input[3] = 6;
		input[4] = 9;
		input[5] = 8;
		input[6] = 7;
		input[7] = 12;
		input[8] = 12;
		input[9] = 12;
		
		
		int result = sfbc.CountPeaksAndValleys(input);
		System.out.println("result is " + result);
	}

}
