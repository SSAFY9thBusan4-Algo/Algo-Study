package week12.BOJ_20920_S3_영단어암기는괴로워;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	
	private static int N, M; // 단어 개수, 외울단어길이기준

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> words = new HashMap<>();
		for (int i=0; i<N; i++) {
			String s = in.readLine();
			if (s.length() >= M) {
				int cnt = words.getOrDefault(s, 0);
				words.put(s, cnt+1);				
			}
		}
		
		Set<String> wordset = words.keySet();
		List<String> wordlist = new ArrayList<>();
		for (String word:wordset) 
			wordlist.add(word);
		
		wordlist.sort((o1, o2) -> {
			
			int c1 = words.get(o1);
			int c2 = words.get(o2);
			
			if (c1==c2) {
				if (o1.length()==o2.length()) {
					return o1.compareTo(o2);
				}
				else {
					return o2.length()-o1.length();
				}
			}
			else {
				return c2-c1;
			}
			
		});
		
		StringBuilder sb = new StringBuilder();
		for (String s : wordlist) sb.append(s).append("\n");
		
		System.out.println(sb);
		
	}
	
}
