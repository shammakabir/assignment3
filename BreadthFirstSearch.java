package assignment3;

import java.util.ArrayList;
import java.util.Set;

class WordNode {
	String word;
	int length = 0;
	
	public WordNode(String w, int l) {
		word = w;
		length = l;
	}
}

public class BreadthFirstSearch {
	public int breadthFirstSearch(String start, String end, Set<String> dictionary) {
		ArrayList<WordNode> storage = new ArrayList<WordNode>();
		storage.add(new WordNode(start,0));
		int length = 0;
		while (storage.isEmpty() != true) {
			if (start.equals(end)) { //if start and end words are the same
				return 0;
		}
			
			char[] check = start.toCharArray();
			for (int i = 0; i < check.length; i++) { //changing every letter of start word 
				for (char j = 'a'; j <= 'z'; j++){ //changing each letter from a - z
					char temp = check[i];
					if (check[i] != j) {
						check[i] = j;
					}
					String checkWord = new String(check);
					if (dictionary.contains(checkWord)) {
						length += 1;
						storage.add(new WordNode(checkWord, length)); // add word to the queue
						dictionary.remove(checkWord); //word has been visited
					}
					
					check[i] = temp; //put the original letter back
				}
			}
			
			
		}
		return length;
	}

}
