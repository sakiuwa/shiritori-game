/* 
 * Later implement a machine learning capability to build own
 * dictionary and add words that is not known by the game
 */


import java.io.File;
import java.io.FileReader;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ShiritoriMachine {

	public final static String WORDBANK_FILE = "small_wordbank.txt";
	
	public static void main(String args[]) throws IOException {
		
		// SET UP DICTIONARY --------------------------------------------------------
		File file = new File(WORDBANK_FILE);
		
		// Create empty HashSet
		ShiritoriDictionary dictionary = new ShiritoriDictionary();
		HashSet<String> used = new HashSet<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String text = null;
		
		while ((text = reader.readLine()) != null) {
			String[] words = text.split(",");
			for (String word : words) {
				dictionary.add(word);
			}
		}
		
		
		
		// EXPLAIN SHIRITORI --------------------------------------------------------
		Scanner scan = new Scanner(System.in);
		String inputString = "";
		
		System.out.println("Let's play Shiritori!");
		System.out.print("Do you know how to play? (y/n) : ");
		inputString = scan.nextLine();
		inputString = inputString.toLowerCase();
		if (inputString.equals("n")) {
			System.out.println("This game is the English version of the Japanese wordplay game, Shiritori.");
			System.out.println("The game will start by you giving a word. I will then give you a word that");
			System.out.println("starts with the last letter of your initial word. Then, you will give a new");
			System.out.println("word that starts with the last letter of my word. If you break the rule, or");
			System.out.println("repeat a previously used word, you lose. If I run out of new words to use, ");
			System.out.println("I lose.");
			System.out.println();
		}
		System.out.println("Let's get started!");
		System.out.println();
		
		
		
		
		
		// PLAY SHIRITORI --------------------------------------------------------
		System.out.println("----------SHITORI----------");
		System.out.println();
		
		String word = "";
		char last = '-'; // First letter should equal this!
		Random rand = new Random();
		//char firstLetter = (char) (rand.nextInt(26) + 97);
		
		
		System.out.println("You start! What's your first word?");
		while (true) {
			System.out.print("Your word: ");
			word = scan.nextLine();
			word = word.trim();
			word = word.toLowerCase();
			
			// Makes sure the word is at least 2 letters long
			while (word.length() <= 1) {
				System.out.println("Give me a word that's at least 2 letters long.");
				System.out.print("Your word: ");
				word = scan.nextLine();
				word = word.trim();
			}
			
			if (used.contains(word)) {
				System.out.println("We already used " + word + "!");
				System.out.println("I WIN!");
				break;
			}
			
			char start = word.charAt(0);
			if (last != '-' && start != last) {
				System.out.println(word + " doesn't start with " + last + "!");
				System.out.println("I WIN!");
				break;
			} else {
				used.add(word);
				last = word.charAt(word.length() - 1);
				ArrayList<String> currList = dictionary.getLetterList(last);
				String nextWord = nextWord(currList, used);
				
				if (nextWord.equals("")) {
					System.out.println("I'm out of words!");
					System.out.println("YOU WIN!");
					break;
				} else {
					System.out.println("My word: " + nextWord);
					last = nextWord.charAt(nextWord.length() - 1);
				}
			}
		}
		reader.close();
		
	}
	
	private static String nextWord(ArrayList<String> list, HashSet<String> used) {
		ArrayList<String> clone = (ArrayList<String>) list.clone();
		String word = nextWordHelper(clone, used);
		used.add(word);
		return word;
	}
	
	private static String nextWordHelper(ArrayList<String> list, HashSet<String> used) {
		String word = "";
		if (!list.isEmpty()) {
			Random rand = new Random();
			word = list.get(rand.nextInt(list.size()));
			if (used.contains(word)) {
				list.remove(word);
				word = nextWordHelper(list, used);
			}
		}
		return word;
	}
	
	
}
