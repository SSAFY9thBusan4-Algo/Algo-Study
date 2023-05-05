package src.week12.BOJ_20920_S3_영단어암기는괴로워;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	
	private static class Word implements Comparable<Word>{
		String s;
		int len, fre;
		
		public Word(String s, int len, int fre) {
			super();
			this.s = s;
			this.len = len;
			this.fre = fre;
		}
		
		@Override
		public int compareTo(Word o) {
			if(this.fre != o.fre) return o.fre - this.fre;
			if(this.len != o.len) return o.len - this.len;
			return this.s.compareTo(o.s);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		
		// 1.빈도수 세기
		HashMap<String, Integer> word = new HashMap<>();
		for(int i = 0 ; i < N ; i++) {
			String s = br.readLine();
			if(s.length() >= M) word.put(s, word.getOrDefault(s, 0)+1);
		}
		
		// 2. 길이, 사전 순으로 정렬
		PriorityQueue<Word> sortedWord = new PriorityQueue<>();
		word.forEach((s, r) -> sortedWord.add(new Word(s, s.length(), r)));
		
		// 3. 출력
		StringBuilder sb = new StringBuilder();
		while(!sortedWord.isEmpty()) {
			sb.append(sortedWord.poll().s).append('\n');
		}
		
		System.out.println(sb);
		
	}
}
