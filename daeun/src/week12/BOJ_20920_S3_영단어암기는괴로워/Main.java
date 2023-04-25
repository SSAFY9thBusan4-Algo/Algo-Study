import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;

public class Main {
	static class Word implements Comparable<Word>{
		String word;
		int cnt;
		public Word(String word, int cnt) {
			super();
			this.word = word;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Word o) {
			if(this.cnt != o.cnt) {
				return o.cnt - this.cnt;
			}
			if(this.word.length() != o.word.length()) {
				return o.word.length() - this.word.length();
			}
			return this.word.compareTo(o.word);
		}
	}
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashMap<String, Word> map = new HashMap<>();
		List<Word> list = new ArrayList<>();
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			if(str.length()>=M) {
				if(map.containsKey(str)) {
					map.get(str).cnt++;
				}
				else {
					Word word = new Word(str, 1);
					map.put(str, word);
					list.add(word);
				}
			}
		}
		
		Collections.sort(list); //정렬
    
		for(Word w: list) {
			sb.append(w.word).append("\n");
		}
		System.out.println(sb);
	}
}
