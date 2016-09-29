/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Shamma Kabir
 * sk38422
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;


public class Main {
	
	private static ArrayList<WordNode> vertexList;
	private static String head;
	private static String tail;
	
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		//code for testing
		ArrayList<String> words = parse(kb);
		String start = words.get(0);
		String end = words.get(1); //making sure parse works right
		ArrayList<String> ladder = getWordLadderBFS(start,end);
		for (int i = 0; i < ladder.size(); i++) {
			System.out.println(ladder.get(i));
		}
		// the print for loop might be able to just go in the print function -- check that out!
		// TODO methods to read in words, output ladder 
	}
	
	public static void initialize() {
		vertexList= new ArrayList<WordNode>();
		createnodelist(makeDictionary());
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		ArrayList<String> input = new ArrayList<String>();
		while (input.size() != 2){
		String command = keyboard.nextLine();
		command = command.trim();
			if (command.equals("/quit")){
				return null;
			}
			String[] parser = command.split(" ");
			for (String s: parser){
				input.add(s);
			}
		}
		return input;
		
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		WordNode rung = new WordNode("DNE");
		head = start;
		tail = end;
		ArrayList<String> ladder = new ArrayList<String>();
		if(DFS(getNode(head))){
			rung = (getNode(tail)).parent;
			while(rung.parent != null){
			ladder.add(0,rung.word);
			rung=rung.parent;
			}
		}
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		// TODO more code
		clearGraph();
		return ladder; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dictionary = makeDictionary();
		LinkedList<ArrayList<String>> queue = new LinkedList<ArrayList<String>>(); //making a queue of ladders
		
	    	ArrayList<String> storage = new ArrayList<String>();
	    	ArrayList<String> none = new ArrayList<String>();
		
	    	storage.add(start); //storage is our first ladder
		queue.add(storage); //add storage to queue
		dictionary.remove(start.toUpperCase()); //not killing real dictionary b/c it's we're going through a generated one
		//specifically for this function
	    	int j = 0;
		while (queue.isEmpty() != true) {
			ArrayList<String> top = queue.remove(); 
			j = top.size() - 1;
			String firstWord = top.get(j);
			if (firstWord.equals(end)) {
				return top;
			}
			char[] check = firstWord.toCharArray();
			for (int i = 0; i < check.length; i++) { //changing every letter of start word 
				for (char k = 'a'; k <= 'z'; k++){ //changing each letter from a - z
					char temp = check[i];
					if (check[i] != k) {
						check[i] = k;
					}
					String checkWord = new String(check);
					ArrayList<String> ladder = new ArrayList<String>();
					if (dictionary.contains(checkWord.toUpperCase())) {
						ladder.addAll(top);
						ladder.add(checkWord);
						queue.add(ladder); 
						dictionary.remove(checkWord.toUpperCase()); //bc you don't wanna run into it again 
					}
					
					check[i] = temp; //put the original letter back
				}
			}
			
		}
		
		
		return none;
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		int rung = 0;
		if (ladder.size() > 0) {
			rung = ladder.size();
			String start = ladder.get(0);
			String finish = ladder.get(rung - 1);
			System.out.println("a "+rung+"-rung word ladder exists between "+start+" and "+finish+".");
			for (int i = 0; i < ladder.size(); i++) {
			System.out.println(ladder.get(i));
			}
		}
		else if (ladder.size() == 0) {
			//do this
		}	
	}
	
	/**
	 * Test to see if a word node linked to a string already exist
	 * if not creates the node
	 * @param s is string used to create a node, also test if node already exist
	 * @return the index of the node
	 */
	
	public static WordNode getNode(String s){
		WordNode returnnode = new WordNode("DNE");
		if(vertexList != null){
			for (WordNode Node: vertexList){
				if(Node.word.equals(s)){
					returnnode = Node;
					break;
				}		
			}
		}
		return returnnode;
	}
	
	private static void createnodelist(Set<String> dictionary){
		for(String s : dictionary){
			vertexList.add(new WordNode(s.toLowerCase()));
			}
		}
	
	
	private static void clearGraph(){
		for (WordNode n : vertexList){
			n.visited = false;
			n.parent = null;
			n.closeness=0;
			n.connectwords.clear();
		}
	}


	private static boolean DFS(WordNode node){
		node.visited=true;
		node.CCwords(vertexList);
		if (node.word.equals(tail)){ return true;}
		else if( node.connectwords.size() == 0){return false;}
		node.sort(tail);
		for( WordNode s : node.connectwords){
			if(!(s.visited)){ 
				if(DFS(s)){return true;
				}
			}
		}
		return false;
	}

	// TODO
	// Other private static methods here
}
