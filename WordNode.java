package assignment3;

import java.util.*;
;

public class WordNode {
	public String word;
	public WordNode parent;
	public boolean visited;

	
	public WordNode(String s, Set<String> dic) {
		word = s;
		visited = false;
		parent=null;
	}

	public void changeParent(WordNode x){
		parent=x;
	}
}
