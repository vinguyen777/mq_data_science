package student;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import org.junit.Test;

public class TweetCollection {

	// TODO: add appropriate data types

	private TreeMap<String, Tweet> colTweet;
	private TreeMap<String, Tweet> graph;
	// Graph implemented using a TreeMap: key is tweet id label,
	// value is the tweet object, which contains the neighbour list
	// for that tweet
	private TreeMap<String, String> sent;
	private TreeMap<String, Vector<String>> finegrained;

	public TweetCollection() {
		// Constructor

		// TODO

		colTweet = new TreeMap<String, Tweet>();
		graph = new TreeMap<String, Tweet>();
		sent = new TreeMap<String, String>();
		finegrained = new TreeMap<String, Vector<String>>();
	}

	/*
	 * functions for accessing individual tweets
	 */

	public Tweet getTweetByID(String ID) {
		// PRE: -
		// POST: Returns the Tweet object that with tweet ID

		// TODO
		Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			if (pairs.getValue().getID().equals(ID)) {
				return pairs.getValue();
			}
		}
		return null;
	}

	/*
	 * HELPER functions for setting all vertices of the graph to unvisited ones
	 */

	public void setUnmarked() {
		// Sets all vertices to be unmarked e.g. after traversal
		Iterator<Map.Entry<String, Tweet>> tmit = graph.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			t.setUnmarked();
		}
	}

	/*
	 * 
	 */

	/*
	 * HELPER functions for setting all vertices of the graph to unpropagated ones
	 */

	public void setUnpropagated() {
		// Sets all vertices to be unpropagated e.g. after label propagation
		Iterator<Map.Entry<String, Tweet>> tmit = graph.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			t.setUnpropagated();
		}
	}

	/*
	 * 
	 */

	public Integer numTweets() {
		// PRE: -
		// POST: Returns the number of tweets in this collection

		// TODO

		return colTweet.size();
	}

	/*
	 * functions for accessing sentiment words
	 */

	public Polarity getBasicSentimentWordPolarity(String w) {
		// PRE: w not null, basic sentiment words already read in from file
		// POST: Returns polarity of w

		// TODO

		Iterator<Map.Entry<String, String>> tmit = sent.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, String> pairs = tmit.next();
			if (pairs.getKey().equals(w.toLowerCase())) {
				if (pairs.getValue().equals("positive")) {
					return Polarity.POS;
				} else if (pairs.getValue().equals("negative")) {
					return Polarity.NEG;
				} else if (pairs.getValue().equals("neutral")) {
					return Polarity.NEUT;
				}
			}

		}

		return Polarity.NONE;
	}

	public Polarity getFinegrainedSentimentWordPolarity(String w) {
		// PRE: w not null, finegrained sentiment words already read in from file
		// POST: Returns polarity of w

		// TODO

		Iterator<Map.Entry<String, Vector<String>>> tmit = finegrained.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Vector<String>> pairs = tmit.next();
			if (pairs.getKey().equals(w.toLowerCase())) {
				if (pairs.getValue().get(1).equals("positive")) {
					return Polarity.POS;
				} else if (pairs.getValue().get(1).equals("negative")) {
					return Polarity.NEG;
				} else if (pairs.getValue().get(1).equals("neut")) {
					return Polarity.NEUT;
				}
			}
		}
		return Polarity.NONE;
	}

	public Strength getFinegrainedSentimentWordStrength(String w) {
		// PRE: w not null, finegrained sentiment words already read in from file
		// POST: Returns strength of w

		// TODO

		Iterator<Map.Entry<String, Vector<String>>> tmit = finegrained.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Vector<String>> pairs = tmit.next();
			if (pairs.getKey().equals(w.toLowerCase())) {
				if (pairs.getValue().get(0).equals("weaksubj")) {
					return Strength.WEAK;
				} else if (pairs.getValue().get(0).equals("strongsubj")) {
					return Strength.STRONG;
				}
			}
		}
		return Strength.NONE;

	}

	/*
	 * functions for reading in tweets
	 * 
	 */

	public void ingestTweetsFromFile(String fInName) throws IOException, CsvException {
		// PRE: -
		// POST: Reads tweets from .csv file, stores in data structure

		// NOTES
		// Data source, file format description at
		// http://help.sentiment140.com/for-students
		// Using opencsv reader: https://zetcode.com/java/opencsv/

		try (CSVReader reader = new CSVReader(new FileReader(fInName))) {
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) { // nextLine[] is an array of values from the line

				Tweet tw = new Tweet(nextLine[0], // gold polarity
						nextLine[1], // ID
						nextLine[2], // date
						nextLine[4], // user
						nextLine[5]); // text
				// TODO: insert tweet tw into appropriate data type

				colTweet.put(tw.getID(), tw);
			}
		}
	}

	/*
	 * functions for sentiment words
	 */

	public void importBasicSentimentWordsFromFile(String fInName) throws IOException {
		// PRE: -
		// POST: Read in and store basic sentiment words in appropriate data type

		// TODO

		try (BufferedReader br = new BufferedReader(new FileReader(fInName))) {

			String line;
			while ((line = br.readLine()) != null) {
				String[] wrds = line.split(" ");
				sent.put(wrds[0], wrds[1]);
			}
		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	public void importFinegrainedSentimentWordsFromFile(String fInName) throws IOException {
		// PRE: -
		// POST: Read in and store finegrained sentiment words in appropriate data type

		// TODO

		try (BufferedReader br = new BufferedReader(new FileReader(fInName))) {

			String line;
			while ((line = br.readLine()) != null) {
				Vector<String> types = new Vector<String>();
				String[] nospace = line.split(" ");
				String word = nospace[2].split("=")[1];
				types.add(nospace[0].split("=")[1]);
				types.add(nospace[5].split("=")[1]);
				finegrained.put(word, types);
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

	public Boolean isBasicSentWord(String w) {
		// PRE: Basic sentiment words have been read in and stored
		// POST: Returns true if w is a basic sentiment word, false otherwise

		// TODO

		Iterator<Map.Entry<String, String>> tmit = sent.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, String> pairs = tmit.next();
			if (pairs.getKey().equals(w.toLowerCase())) {
				if (pairs.getValue().equals("positive") || pairs.getValue().equals("negative")
						|| pairs.getValue().equals("neutral")) {
					return true;
				}
			}

		}

		return false;
	}

	public Boolean isFinegrainedSentWord(String w) {
		// PRE: Finegrained sentiment words have been read in and stored
		// POST: Returns true if w is a finegrained sentiment word, false otherwise

		// TODO

		Iterator<Map.Entry<String, Vector<String>>> tmit = finegrained.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Vector<String>> pairs = tmit.next();
			if (pairs.getKey().equals(w.toLowerCase())) {
				if (pairs.getValue().get(0).equals("weaksubj") || pairs.getValue().get(0).equals("strongsubj")) {
					return true;
				}
			}

		}

		return false;

	}

	public void predictTweetSentimentFromBasicWordlist() {
		// PRE: Finegrained word sentiment already imported
		// POST: For all tweets in collection, tweet annotated with predicted sentiment
		// based on count of sentiment words in sentWords

		// TODO

		int pos, neg;
		Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
		while (tmit.hasNext()) {
			pos = 0;
			neg = 0;
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			String[] words = t.getWords();
			for (int i = 0; i < words.length; i++) {
				if (isBasicSentWord(words[i])) {
					if (getBasicSentimentWordPolarity(words[i].toLowerCase()).equals(Polarity.POS)) {
						pos++;
					} else if (getBasicSentimentWordPolarity(words[i].toLowerCase()).equals(Polarity.NEG)) {
						neg++;
					}
				}
			}
			if (pos == 0 && neg == 0) {
				t.setPredictedPolarity(Polarity.NONE);
			} else if (pos > neg) {
				t.setPredictedPolarity(Polarity.POS);
			} else if (pos < neg) {
				t.setPredictedPolarity(Polarity.NEG);
			} else {
				t.setPredictedPolarity(Polarity.NEUT);
			}
		}

	}

	public void predictTweetSentimentFromFinegrainedWordlist(Integer strongWeight, Integer weakWeight) {
		// PRE: Finegrained word sentiment already imported
		// POST: For all tweets in v, tweet annotated with predicted sentiment
		// based on count of sentiment words in sentWords

		// TODO
		int pos, neg;
		Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
		while (tmit.hasNext()) {
			pos = 0;
			neg = 0;
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			String[] words = t.getWords();
			for (int i = 0; i < words.length; i++) {
				if (isFinegrainedSentWord(words[i])) {
					if (getFinegrainedSentimentWordPolarity(words[i].toLowerCase()).equals(Polarity.POS)) {
						if (getFinegrainedSentimentWordStrength(words[i].toLowerCase()).equals(Strength.STRONG)) {
							pos += strongWeight;
						} else if (getFinegrainedSentimentWordStrength(words[i].toLowerCase()).equals(Strength.WEAK)) {
							pos += weakWeight;
						}
					} else if (getFinegrainedSentimentWordPolarity(words[i].toLowerCase()).equals(Polarity.NEG)) {
						if (getFinegrainedSentimentWordStrength(words[i].toLowerCase()).equals(Strength.STRONG)) {
							neg += strongWeight;
						} else if (getFinegrainedSentimentWordStrength(words[i].toLowerCase()).equals(Strength.WEAK)) {
							neg += weakWeight;
						}
					}
				}
			}
			if (pos == 0 && neg == 0) {
				t.setPredictedPolarity(Polarity.NONE);
			} else if (pos > neg) {
				t.setPredictedPolarity(Polarity.POS);
			} else if (pos < neg) {
				t.setPredictedPolarity(Polarity.NEG);
			} else {
				t.setPredictedPolarity(Polarity.NEUT);
			}
		}

	}

	/*
	 * functions for inverse index
	 * 
	 */

	public Map<String, Vector<String>> importInverseIndexFromFile(String fInName) throws IOException {
		// PRE: -
		// POST: Read in and returned contents of file as inverse index
		// invIndex has words w as key, IDs of tweets that contain w as value

		// TODO

		Map<String, Vector<String>> inv = new TreeMap<String, Vector<String>>();
		Vector<String> vec;
		String words = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fInName))) {

			String line;
			while ((line = br.readLine()) != null) {
				vec = new Vector<String>();
				String[] wrds = line.split(" ");
				words = wrds[0];
				String[] index = wrds[1].split(",");
				for (int i = 0; i < index.length; i++) {
					vec.addElement(index[i]);
				}
				inv.put(words, vec);
			}

			return inv;
		}
	}

	/*
	 * HELPER functions for collection inverse index
	 */

	public Map<String, Vector<String>> CollectionInverseIndex(Map<String, Vector<String>> invIndex) {
		TreeMap<String, Vector<String>> colInv = new TreeMap<String, Vector<String>>();
		Vector<String> colVec;
		Iterator<Map.Entry<String, Vector<String>>> conn = invIndex.entrySet().iterator();
		while (conn.hasNext()) {
			colVec = new Vector<String>();
			Map.Entry<String, Vector<String>> curEdge = conn.next();
			Vector<String> vec = curEdge.getValue();
			Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
			while (tmit.hasNext()) {
				Map.Entry<String, Tweet> pairs = tmit.next();
				for (int i = 0; i < vec.size(); i++) {
					if (pairs.getValue().getID().equals(vec.get(i))) {
						colVec.add(pairs.getValue().getID());
					}
					// Convert the vector into the one with unique elements
					LinkedHashSet<String> lhSet = new LinkedHashSet<String>(colVec);
					colVec.clear();
					colVec.addAll(lhSet);
				}
			}
			if (colVec.size() != 0) {
				colInv.put(curEdge.getKey(), colVec);
			}
		}
		return colInv;
	}

	/*
	 * 
	 */

	/*
	 * functions for graph construction
	 */

	public void constructSharedWordGraph(Map<String, Vector<String>> invIndex) {
		// PRE: invIndex has words w as key, IDs of tweets that contain w as value
		// POST: Graph constructed, with tweets as vertices,
		// and edges between them if they share a word

		// TODO

		graph.clear();
		Map<String, Vector<String>> colInv = CollectionInverseIndex(invIndex);
		Iterator<Map.Entry<String, Vector<String>>> conn = colInv.entrySet().iterator();
		while (conn.hasNext()) {
			Map.Entry<String, Vector<String>> curEdge = conn.next();
			Vector<String> vec = curEdge.getValue();

			if (vec.size() <= 1) {
				if (!graph.containsKey(vec.get(0))) {
					graph.put(vec.get(0), getTweetByID(vec.get(0)));
				}

			} else {
				for (int i = 0; i < vec.size(); i++) {
					if (!graph.containsKey(vec.get(i))) {
						graph.put(vec.get(i), getTweetByID(vec.get(i)));
					}
					for (int j = 0; j < vec.size(); j++) {
						if (j != i) {
							graph.get(vec.get(i)).addNeighbour(vec.get(j));
						}

					}
				}
			}

		}

	}

	public Integer numConnectedComponents() {
		// PRE: -
		// POST: Returns the number of connected components

		// TODO

		Iterator<Map.Entry<String, Tweet>> tmit = graph.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			if (!t.isMarked()) {
				this.depthFirstTraversal(t.getID());
				return numConnectedComponents() + 1;
			}
			if (t.getID().equals(graph.lastKey())) {
				return 0;
			}
		}
		setUnmarked();
		return 0;
	}

	/*
	 * HELPER functions for depth first traversal
	 */

	public void depthFirstTraversal(String ID) {
		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		// (for just the connected component containing ID)

		Stack<String> s = new Stack<String>();

		//System.out.print(" " + ID);
		s.push(ID);
		getTweetByID(ID).setMarked();

		while (!s.isEmpty()) { // while not all vertices visited
			ID = s.peek();
			Vector<String> adjList = getTweetByID(ID).getNeighbourTweetIDs();
			Iterator<String> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getTweetByID(ID).isMarked())
				ID = vIt.next(); // skip over visited neighbours

			if (getTweetByID(ID).isMarked()) { // only occurs if all neighbours visited
				s.pop(); // remove from stack
			} else { // v is an unvisited neighbour
				s.push(ID); // add to stack
				getTweetByID(ID).setMarked();
				//System.out.print(" " + ID);
			}
		}

	}

	/*
	 * 
	 */

	public void annotateConnectedComponents() {
		// PRE: -
		// POST: Annotates graph so that it is partitioned into components

		// TODO

		depthFirstTraversal(colTweet.firstKey());
		setUnmarked();

	}

	public Integer componentSentLabelCount(String ID, Polarity p) {
		// PRE: Graph components are identified, ID is a valid tweet
		// POST: Returns count of labels corresponding to Polarity p in component
		// containing ID

		// TODO

		setUnmarked();
		Integer cnt = 0;

		Stack<String> s = new Stack<String>();

		System.out.print(" " + ID);
		s.push(ID);
		getTweetByID(ID).setMarked();
		if (getTweetByID(ID).getPredictedPolarity().equals(p)) {
			cnt++;
		}

		while (!s.isEmpty()) { // while not all vertices visited
			ID = s.peek();
			Vector<String> adjList = getTweetByID(ID).getNeighbourTweetIDs();
			Iterator<String> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getTweetByID(ID).isMarked())
				ID = vIt.next(); // skip over visited neighbours

			if (getTweetByID(ID).isMarked()) { // only occurs if all neighbours visited
				s.pop(); // remove from stack
			} else { // v is an unvisited neighbour
				s.push(ID); // add to stack
				if (getTweetByID(ID).getPredictedPolarity().equals(p)) {
					cnt++;
				}
				getTweetByID(ID).setMarked();
				System.out.print(" " + ID);
			}
		}

		return cnt;
	}

	public void propagateLabelAcrossComponent(String ID, Polarity p, Boolean keepPred) {
		// PRE: ID is a tweet id in the graph
		// POST: Labels tweets in component with predicted polarity p
		// (if keepPred == T, only tweets w pred polarity None; otherwise all tweets)

		// TODO

		Stack<String> s = new Stack<String>();

		//System.out.print(" " + ID);
		s.push(ID);
		getTweetByID(ID).setMarked();
		if (keepPred.equals(true)) {
			if (getTweetByID(ID).getPredictedPolarity().equals(Polarity.NONE)) {
				getTweetByID(ID).setPredictedPolarity(p);
				getTweetByID(ID).setPropagated();
			}
		} else {
			getTweetByID(ID).setPredictedPolarity(p);
			getTweetByID(ID).setPropagated();
		}
		
		getTweetByID(ID).setPropagated();

		while (!s.isEmpty()) { // while not all vertices visited
			ID = s.peek();
			Vector<String> adjList = getTweetByID(ID).getNeighbourTweetIDs();
			Iterator<String> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getTweetByID(ID).isMarked())
				ID = vIt.next(); // skip over visited neighbours

			if (getTweetByID(ID).isMarked()) { // only occurs if all neighbours visited
				s.pop(); // remove from stack
			} else { // v is an unvisited neighbour
				s.push(ID); // add to stack
				if (keepPred.equals(true)) {
					if (getTweetByID(ID).getPredictedPolarity().equals(Polarity.NONE)) {
						getTweetByID(ID).setPredictedPolarity(p);
						//getTweetByID(ID).setPropagated();
					}
				} else {
					getTweetByID(ID).setPredictedPolarity(p);
					//getTweetByID(ID).setPropagated();
				}
				getTweetByID(ID).setPropagated();
				getTweetByID(ID).setMarked();
				//System.out.print(" " + ID);
			}
		}

	}

	public void propagateMajorityLabelAcrossComponents(Boolean keepPred) {
		// PRE: Components are identified
		// POST: Tweets in each component are labelled with the majority sentiment for
		// that component
		// Majority label is defined as whichever of POS or NEG has the larger count;
		// if POS and NEG are both zero, majority label is NONE
		// otherwise, majority label is NEUT
		// If keepPred is True, only tweets with predicted label None are labelled in
		// this way
		// otherwise, all tweets in the component are labelled in this way

		// TODO

		setUnmarked();
		setUnpropagated();

		int pos, neg;
		Polarity save;
		Iterator<Map.Entry<String, Tweet>> tmit = graph.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			Tweet t = pairs.getValue();
			if (!t.isPropagated()) {
				pos = componentSentLabelCount(t.getID(), Polarity.POS);
				neg = componentSentLabelCount(t.getID(), Polarity.NEG);
				if ((pos == 0) && (neg == 0)) {
					save = Polarity.NONE;
				} else if (pos > neg) {
					save = Polarity.POS;
				} else if (pos < neg) {
					save = Polarity.NEG;
				} else {
					save = Polarity.NEUT;
				}

				propagateLabelAcrossComponent(pairs.getKey(), save, keepPred);//?needs to set unpropagated again?
			}
		}

	}

	/*
	 * functions for evaluation
	 */

	public Double accuracy() {
		// PRE: -
		// POST: Calculates and returns accuracy of labelling

		// TODO

		Double numCrt = 0.0;
		Double numPred = 0.0;
		Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			if (pairs.getValue().getPredictedPolarity().equals(Polarity.NONE)
					|| pairs.getValue().getGoldPolarity().equals(Polarity.NONE)) {
				;
			} else {
				numPred += 1;
				if (pairs.getValue().correctlyPredictedPolarity()) {
					numCrt += 1;
				}
			}
		}
		if (numPred == 0) {
			return 0.0;
		}
		return (numCrt / numPred);

	}

	public Double coverage() {
		// PRE: -
		// POST: Calculates and returns coverage of labelling

		// TODO
		Double numPred = 0.0;
		Iterator<Map.Entry<String, Tweet>> tmit = colTweet.entrySet().iterator();
		while (tmit.hasNext()) {
			Map.Entry<String, Tweet> pairs = tmit.next();
			if (pairs.getValue().getPredictedPolarity().equals(Polarity.NONE)
					|| pairs.getValue().getGoldPolarity().equals(Polarity.NONE)) {
				;
			} else {
				numPred += 1;
			}
		}
		if (numTweets().equals(0)) {
			return 0.0;
		}
		return (numPred / numTweets());

	}

	public static void main(String[] args) {

	}
}
