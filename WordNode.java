package assignment3;

import java.util.*;
;

public class WordNode {
	public String word;
	public WordNode parent;
	public boolean visited;
	public ArrayList<WordNode> connectwords;
	public int closeness;

	
	public WordNode(String s) {
		word = s;
		visited = false;
		parent=null;
		connectwords = new ArrayList<WordNode>(); 
	}

	/**
	 * create the list of connected words for a given node
	 * @param s list of wordnodes to based connection off of
	 */
	/**
	 * @param s
	 */
	public void CCwords( ArrayList<WordNode> s){
		char checker2[] = this.word.toCharArray();
		int check;
		for (WordNode c : s){
			check =0;
			char checker1[] = c.word.toCharArray();
					
			for(int i=0; i<checker1.length; i++){
				if (checker1[i] == checker2[i]){ check++; }
			}
			if(check == 4 && c.visited != true
					){ connectwords.add(c);
					c.parent=this;}
		}
	}
	
	/**
	 * sorts an the connectwords list in order of closeness to a given word
	 * @param e end word 
	 */
	public void sort(String e){
		char[] arr1 = e.toCharArray();
		char[] arr2;
		int check=0;
		for(WordNode N : connectwords){
			arr2 = N.word.toCharArray();
			for(int i =0 ; i < ((arr1).length); i++){
			if(arr1[i]==arr2[i]){check++;}
			}
			N.closeness=check;
		}
		WordNode temp;
		int j;
		for(int i=1; i< connectwords.size(); i++){
			temp = connectwords.get(i);
			j = i-1;
			while(j >= 0 && connectwords.get(j).closeness<temp.closeness){
					connectwords.set(j+1,connectwords.get(j));
					j= j-1;
				}
				connectwords.set(j+1, temp);
			}
		}
		
	
}