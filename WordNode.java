package assignment3;

import java.util.*;
;

public class WordNode {
	public String word;
	public WordNode parent;
	public boolean visited;
	public static ArrayList<WordNode> List;

	
	public WordNode(String s) {
		word = s;
		visited = false;
		parent=null;
		List.add(this);
	}
	public String getWord(){
		return word;
	}

}