package demos.algorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import zgl.util.Utils;

public class WordTrieTest {
	public static void main(String[] args) {
		WordTrie wt = new WordTrie();
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:/code/files/words.txt"));
			String line;
			while((line = br.readLine()) != null){
				wt.addWord(line.split(" ")[1].toLowerCase());
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> list = wt.searchWord("a");
		Utils.printList(list);
		list = wt.prefixSearchWord("an");
		Utils.printList(list);
		list = wt.prefixSearchWord("");
		Utils.printList(list);
		
	}
}
