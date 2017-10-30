/**
 * 
 */
package part2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.lang.*;

/**
 * @author Zhou
 *
 */
public class SolutionForTransformation {

	private int numberOfBattles;
	private List<Transformer> winingTeam;
	private List<Transformer> losingTeam;

	public int getNumberOfBattles() {
		return numberOfBattles;
	}

	public List<Transformer> getWiningTeam() {
		return winingTeam;
	}

	public List<Transformer> getLosingTeam() {
		return losingTeam;
	}

	public void decideWhoIsWining(String[] input) {
		// int to count autobots destroyed
		int countAutoDestroyed = 0;
		// int to count deceptions destroyed
		int countDeceptionDestryoed = 0;
		// count battles
		int countBattles = 0;

		// declare a list to store transformers of autobots
		List<Transformer> autobots = new ArrayList<Transformer>();

		// declare a list to store transformers of Deception
		List<Transformer> deceptions = new ArrayList<Transformer>();

		// go through the input array and parse everything
		try {
			for (int i = 0; i < input.length; i++) {
				String cur[] = input[i].split(",");
				Transformer transformer = new Transformer(cur[0].trim(), cur[1].trim(), cur[2].trim(), cur[3].trim(),
						cur[4].trim(), cur[5].trim(), cur[6].trim(), cur[7].trim(), cur[8].trim(), cur[9].trim());
				if (transformer.getTeam().equalsIgnoreCase("D")) {
					transformer.setTeam("Decepticons");
					deceptions.add(transformer);
				}
				if (transformer.getTeam().equalsIgnoreCase("A")) {
					transformer.setTeam("Autobots");
					autobots.add(transformer);
				}
			}
			
			//check either team is empty
			if(autobots.size() == 0){
				this.winingTeam = deceptions;
				throw new Exception("winning team is Deceptions, as of no transformer of Autobots attended");
			}
			
			if(deceptions.size() == 0){
				this.winingTeam = autobots;
				throw new Exception("winning team is Autobots, as of no transformer of Deceprtions attended");
			}
			
			
			// sort autobots list and deception list
			Collections.sort(autobots, new TransformerComparator());
			Collections.sort(deceptions, new TransformerComparator());

			/*
			 * for(int j = 0; j < autobots.size(); j++) {
			 * System.out.println("element is " + autobots.get(j).getName() +
			 * " and rank is " + autobots.get(j).getRank()); }
			 * 
			 * for(int j = 0; j < deceptions.size(); j++) {
			 * System.out.println("element is " + deceptions.get(j).getName() +
			 * " and rank is " + deceptions.get(j).getRank()); }
			 */

			// starting battle

			for (int k = 0; k < autobots.size() && k < deceptions.size(); k++) {
				// if the competitors are Optimus Prime and Predaking
				if (autobots.get(k).getName().equalsIgnoreCase("Optimus Prime")
						&& deceptions.get(k).getName().equalsIgnoreCase("Predaking")) {
					destroyAllTransformers(autobots, deceptions);
					throw new Exception("Optimus Prime vs Predaking, game over, all transformers are destroyed");
				}
				// if autobot side is Optimus Prime and the other side is not
				// predaking
				else if (autobots.get(k).getName().equalsIgnoreCase("Optimus Prime")
						&& !deceptions.get(k).getName().equalsIgnoreCase("Predaking")) {
					deceptions.get(k).setDestroyed(true);
					countBattles++;
					countDeceptionDestryoed++;
				}
				// if deception side is predaking and the other side is not
				// optimus Prime
				else if (!autobots.get(k).getName().equalsIgnoreCase("Optimus Prime")
						&& deceptions.get(k).getName().equalsIgnoreCase("Predaking")) {
					autobots.get(k).setDestroyed(true);
					countBattles++;
					countAutoDestroyed++;
				}
				// if fighter is down 4 or more points of courage and 3 or more
				// points of strength compared to their opponent
				else if (autobots.get(k).getCourage() >= deceptions.get(k).getCourage() + 4
						&& autobots.get(k).getStrength() >= deceptions.get(k).getStrength() + 3) {
					deceptions.get(k).setDestroyed(true);
					countBattles++;
					countDeceptionDestryoed++;
				} else if (autobots.get(k).getCourage() + 4 <= deceptions.get(k).getCourage()
						&& autobots.get(k).getStrength() + 3 <= deceptions.get(k).getStrength()) {
					autobots.get(k).setDestroyed(true);
					countBattles++;
					countAutoDestroyed++;
				}
				// if fight 3 or more points of skill above their opponent
				else if (autobots.get(k).getSkill() >= deceptions.get(k).getSkill() + 3) {
					deceptions.get(k).setDestroyed(true);
					countBattles++;
					countDeceptionDestryoed++;
				} else if (autobots.get(k).getSkill() + 3 <= deceptions.get(k).getSkill()) {
					autobots.get(k).setDestroyed(true);
					countBattles++;
					countAutoDestroyed++;
				}
				// if this is a tie
				else if (autobots.get(k).getOverallRating() == deceptions.get(k).getOverallRating()) {
					deceptions.get(k).setDestroyed(true);
					autobots.get(k).setDestroyed(true);
					countBattles++;
					countAutoDestroyed++;
					countDeceptionDestryoed++;
				}
				// if none of the conditions above satisfy
				else if (autobots.get(k).getOverallRating() > deceptions.get(k).getOverallRating()) {
					deceptions.get(k).setDestroyed(true);
					countBattles++;
					countDeceptionDestryoed++;
				} else if (autobots.get(k).getOverallRating() < deceptions.get(k).getOverallRating()) {
					autobots.get(k).setDestroyed(true);
					countBattles++;
					countAutoDestroyed++;
				}
			}
			// set number of battles
			this.numberOfBattles = countBattles;

			// if # of destroyed autobots is greater than the # of destryoed
			// deception, then deception wins
			if (countAutoDestroyed > countDeceptionDestryoed) {
				this.winingTeam = deceptions;
				this.losingTeam = autobots;
			}

			// if # of destroyed deception is greater than the # of destryoed
			// autobots, then autobots wins
			if (countAutoDestroyed < countDeceptionDestryoed) {
				this.winingTeam = autobots;
				this.losingTeam = deceptions;
			}

			// if # of destroyed deception is equal to the # of destryoed
			// autobots, then autobots wins
			if (countAutoDestroyed == countDeceptionDestryoed) {
				throw new Exception("tie game, no winner and loser");
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void destroyAllTransformers(List<Transformer> autobots, List<Transformer> deceptions) {
		for (int i = 0; i < autobots.size(); i++) {
			autobots.get(i).setDestroyed(true);
		}
		for (int i = 0; i < deceptions.size(); i++) {
			deceptions.get(i).setDestroyed(true);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] input = {
				"PREDAKING,D,10,5,10,8,7,9,9,8",
				"ABOMINUS,D,10,1,3,10,5,10,8,4",
				"Soundwave,D,8,9,2,6,7,5,6,10",
				"BEACHCOMBER,A,3,9,5,9,6,6,1,10",
				"Hubcap,A,4,4,4,4,4,4,4,4",
				"Bluestreak,A,6,6,7,9,5,2,9,7",
				"Optimus Prime,A,10,10,8,10,10,10,8,10"
				};
		SolutionForTransformation sftf = new SolutionForTransformation();
		sftf.decideWhoIsWining(input);
		int battles = sftf.getNumberOfBattles();
		List<Transformer> winingTeam = sftf.getWiningTeam();
		List<Transformer> losingTeam = sftf.getLosingTeam();
		System.out.println(battles + " battles");
		if(winingTeam != null){
			System.out.print("Winning team (" + winingTeam.get(0).getTeam() + "): ");
			for(Transformer val : winingTeam){
				System.out.print(val.getName() + ", ");
			}
			System.out.println();
		}
		if(losingTeam != null){
			System.out.print("Survivors from the losing team (" + losingTeam.get(0).getTeam() + "): ");
			for(Transformer val : losingTeam){
				System.out.print(val.getName() + ", ");
			}
			System.out.println();
		}
		
	}

}
