package assignment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

class WordNode {
	String word;
	int rung;
	
	public WordNode(String w, int r) {
		word = w;
		rung = r;
	}
}

public class BreadthFirstSearch {
	public static void breadthFirstSearch(String start, String end, Set<String> dictionary) {
		LinkedList<WordNode> storage = new LinkedList<WordNode>();
		storage.add(new WordNode(start, 0));
		if (start.equals(end)) { //if start and end words are the same
			return;
			}
		while (storage.isEmpty() != true) {
			WordNode newWord = storage.remove();
			String newWordCheck = newWord.word;
			char[] check = newWordCheck.toCharArray();
			for (int i = 0; i < check.length; i++) { //changing every letter of start word 
				for (char j = 'a'; j <= 'z'; j++){ //changing each letter from a - z
					char temp = check[i];
					if (check[i] != j) {
						check[i] = j;
					}
					String checkWord = new String(check);
					if (dictionary.contains(checkWord)) {
						storage.add(new WordNode(checkWord, newWord.rung + 1)); // add word to the queue
						dictionary.remove(checkWord); //word has been visited
					}
					
					check[i] = temp; //put the original letter back
				}
			}
			
		}
	}

}
