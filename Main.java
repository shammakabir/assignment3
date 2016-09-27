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
	
	private static Set<String> dict;// static variables and constants only here.
	
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
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		String command = keyboard.nextLine();
		command = command.trim();
		if (command.equals("/quit")){
			return null;
		}
		ArrayList<String> input = new ArrayList<String>();
		String[] compare = command.split("");
		input.add(compare[0]);
		input.add(compare[1]);
		return input;
		
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		Set<String> dictionary = makeDictionary();
		LinkedList<ArrayList<String>> queue = new LinkedList<ArrayList<String>>(); //making a queue of ladders
		
	    	ArrayList<String> storage = new ArrayList<String>();
	    	ArrayList<String> none = new ArrayList<String>();
		
	    	storage.add(start); //storage is our first ladder
		queue.add(storage); //add storage to queue
		dictionary.remove(start); //not killing real dictionary b/c it's we're going through a generated one
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
					if (dictionary.contains(checkWord)) {
						ladder.addAll(top);
						ladder.add(checkWord);
						queue.add(ladder); 
						dictionary.remove(checkWord); //bc you don't wanna run into it again 
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
		
	}
	
	/**
	 * Test to see if a word node linked to a string already exist
	 * if not creates the node
	 * @param s is string used to create a node, also test if node already exist
	 * @return the index of the node
	 */
	
	public static int getNode(String s){
		if(WordNode.List != null){
			for (WordNode Node: WordNode.List){
				if(Node.word.equals(s)){
					return WordNode.List.indexOf(Node);
				}		
			}
		}
		new WordNode(s);
		return (WordNode.List.size()-1);
	}	private static boolean checkDic(char[] x, Set<String> dictionary){
		for(String s : dictionary){
			if (s.toCharArray().equals(x)){
				return true;
				
			}
		}
		return false;
	}
	private static ArrayList<String> wordCombos(String s){
		ArrayList<String> x = new ArrayList<String>() ;
		char[] check = s.toCharArray();
		for(int i=0; i < check.length; i++){
			char temp = check[i];
			for(char k='A';k <='Z'; k++){
				check[i]=k;
				if (checkDic(check,dict)){
					String s2 = new String(check);
					x.add(s2);
				}
			}
			check[i]=temp;
		}
		return x;
	}
	
		private static boolean checkDic(char[] x, Set<String> dictionary){
		for(String s : dictionary){
			if (s.toCharArray().equals(x)){
				return true;
				
			}
		}
		return false;
	}
	private static ArrayList<String> wordCombos(String s){
		ArrayList<String> x = new ArrayList<String>() ;
		char[] check = s.toCharArray();
		for(int i=0; i < check.length; i++){
			char temp = check[i];
			for(char k='A';k <='Z'; k++){
				check[i]=k;
				if (checkDic(check,dict)){
					String s2 = new String(check);
					x.add(s2);
				}
			}
			check[i]=temp;
		}
		return x;
	}
	// TODO
	// Other private static methods here
}
