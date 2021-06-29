package student;

import java.util.*;

enum Polarity {
	POS, NEG, NEUT, NONE;
}

enum Strength {
	STRONG, WEAK, NONE;
}

public class Tweet {

	// TODO: Add appropriate data types

	Polarity pgold; // GOLD POLARITY of the tweet
	Polarity pred; // PREDICTED POLARITY of the tweet
	String i; // the id of the tweet
	String d; // the date of the tweet
	String u; // the user that tweeted
	String t; // the text of the tweet
	Vector<String> vectr; // Vector of tweets, to be used predominantly to
							// represent the neighbours of a tweet
	Boolean propagated; // Used to indicate previously propagated
	Boolean marked; // Used to indicate previously visited

	public Tweet(String p, String i, String d, String u, String t) {
		// TODO

		// 0 = negative, 2 = neutral, 4 = positive, _ = not given
		if (p.equals("0")) {
			pgold = Polarity.NEG;
		} else if (p.equals("2")) {
			pgold = Polarity.NEUT;
		} else if (p.equals("4")) {
			pgold = Polarity.POS;
		} else {
			pgold = Polarity.NONE;
		}
		pred = Polarity.NONE;
		this.i = i;
		this.d = d;
		this.u = u;
		this.t = t;
		vectr = new Vector<String>();
		propagated = false;
		marked = false;
	}

	public void addNeighbour(String ID) {
		// PRE: -
		// POST: Adds a neighbour to the current tweet as part of graph structure

		// TODO

		vectr.add(ID);
		LinkedHashSet<String> lhSet = new LinkedHashSet<String>(vectr);

		// clear the vector
		vectr.clear();

		// add all unique elements back to the vector
		vectr.addAll(lhSet);
	}

	public Integer numNeighbours() {
		// PRE: -
		// POST: Returns the number of neighbours of this tweet

		// TODO

		return getNeighbourTweetIDs().size();
	}

	public void deleteAllNeighbours() {
		// PRE: -
		// POST: Deletes all neighbours of this tweet

		// TODO

		vectr.clear();
	}

	public Vector<String> getNeighbourTweetIDs() {
		// PRE: -
		// POST: Returns IDs of neighbouring tweets as vector of strings

		// TODO

		return vectr;
	}

	public Boolean isNeighbour(String ID) {
		// PRE: -
		// POST: Returns true if ID is neighbour of the current tweet, false otherwise

		// TODO

		Vector<String> vec = getNeighbourTweetIDs();
		for (int i = 0; i < vec.size(); i++) {
			if (vec.get(i).equals(ID)) {
				return true;
			}
		}
		return false;
	}

	public Boolean correctlyPredictedPolarity() {
		// PRE: -
		// POST: Returns true if predicted polarity matches gold, false otherwise

		// TODO

		if (pgold.equals(pred)) {
			return true;
		} else {
			return false;
		}
	}

	public Polarity getGoldPolarity() {
		// PRE: -
		// POST: Returns the gold polarity of the tweet

		// TODO

		return pgold;
	}

	public Polarity getPredictedPolarity() {
		// PRE: -
		// POST: Returns the predicted polarity of the tweet

		// TODO
		return pred;
	}

	public void setPredictedPolarity(Polarity p) {
		// PRE: -
		// POST: Sets the predicted polarity of the tweet

		// TODO

		pred = p;
	}

	/*
	 * HELPER functions for marking the visited vertices
	 */

	public void setMarked() {
		marked = true;
	}

	public void setUnmarked() {
		marked = false;
	}

	public Boolean isMarked() {
		return marked;
	}

	/*
	 * 
	 */

	/*
	 * HELPER functions for marking the propagated vertices
	 */

	public void setPropagated() {
		propagated = true;
	}

	public void setUnpropagated() {
		propagated = false;
	}

	public Boolean isPropagated() {
		return propagated;
	}

	/*
	 * 
	 */

	public String getID() {
		// PRE: -
		// POST: Returns ID of tweet

		// TODO

		return i;
	}

	public String getDate() {
		// PRE: -
		// POST: Returns date of tweet

		// TODO

		return d;
	}

	public String getUser() {
		// PRE: -
		// POST: Returns identity of tweeter

		// TODO

		return u;
	}

	public String getText() {
		// PRE: -
		// POST: Returns text of tweet as a single string

		// TODO

		return t;
	}

	public String[] getWords() {
		// PRE: -
		// POST: Returns tokenised text of tweet as array of strings

		if (this.getText() == null)
			return null;

		String[] words = null;

		String tmod = this.getText();
		tmod = tmod.replaceAll("@.*?\\s", "");
		tmod = tmod.replaceAll("http.*?\\s", "");
		tmod = tmod.replaceAll("\\s+", " ");
		tmod = tmod.replaceAll("[\\W&&[^\\s]]+", "");
		tmod = tmod.toLowerCase();
		tmod = tmod.trim();
		words = tmod.split("\\s");

		return words;

	}

}
