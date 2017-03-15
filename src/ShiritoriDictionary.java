import java.util.ArrayList;
import java.util.List;

public class ShiritoriDictionary {
	private LetterList[] table;
	private int size;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class LetterList {
		private ArrayList<String> list;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public LetterList() {
			this.list = new ArrayList<String>();
		}
		
		/* Returns true if the given item is in this chain
		 * Returns false otherwise
		 */
		public boolean contains(String word) {
			return list.contains(word);
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(String word) {
			if (!list.contains(word)) {
				list.add(word);
				return true;
			}
			
			return false;
		}
		
		/* Returns the size of this separate chain
		 */
		public int size() {
			return list.size();
		}
		
		/* Returns the String representation of this separate chain
		 */
		public String toString() {
			return list.toString();
		}
		
		/* Returns the list of WordInfo objects in this chain
		 */
		public ArrayList<String> getElements() {
			return list;
		}
	}
	
	public ShiritoriDictionary() {
		table = new LetterList[26];
		size = 0;
		
		for (int i = 0; i < table.length; i++) {
			table[i] = new LetterList();
		}
	}
	
	// BUIDLING DICTIOINARY ----------------------------------------
	public boolean add(String word) {
		word = word.toLowerCase();
		char first = word.charAt(0);
		List<String> temp = getLetterList(first);
		
		if (!temp.contains(word)) {
			temp.add(word);
			size++;
			return true;
		}
		return false;
	}
	
	
	public ArrayList<String> getLetterList(char ch) {
		int index = (int) (ch - 97);
		return table[index].getElements();
	}
	
}
