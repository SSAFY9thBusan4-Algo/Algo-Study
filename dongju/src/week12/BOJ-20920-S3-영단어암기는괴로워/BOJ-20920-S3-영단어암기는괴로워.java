import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Word {
		String word;
		int cnt;
		
		public Word(String word, int cnt) {
			this.word = word;
			this.cnt = cnt;
		}
	}
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<n; i++) {
			String word = br.readLine();
			
			if(word.length() < m) continue;
			
			int cnt = map.getOrDefault(word, 0);
			map.put(word, cnt+1);
		}
		
		List<Word> list = new ArrayList<>();
		for(String s: map.keySet()) {
			list.add(new Word(s, map.get(s)));
		}
		
		Collections.sort(list, new Comparator<Word>() {

			@Override
			public int compare(Word o1, Word o2) {
				if(o2.cnt == o1.cnt) {
					if(o1.word.length() == o2.word.length()) {
						return o1.word.compareTo(o2.word);
					}
					return o2.word.length() - o1.word.length();
				}
				return o2.cnt - o1.cnt;
			}
		});
		
		for(Word word: list) {
			sb.append(word.word).append("\n");
		}
		
		System.out.println(sb);
	}
}
