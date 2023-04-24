package week12.BOJ_20920_S3_영단어암기는괴로워;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_20920_S3_영단어암기는괴로워 {

	public static class Word implements Comparable<Word> {
		String word; // 단어
		int count; // 해당 단어 개수

		public Word(String word, int count) {
			super();
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Word [word=" + word + ", count=" + count + "]";
		}

		// 단어개수 -> 단어 길이 -> 사전순으로 정렬
		@Override
		public int compareTo(Word o) {
			if (this.count == o.count) {
				if (this.word.length() == o.word.length()) {
					return this.word.compareTo(o.word);
				}
				return o.word.length() - this.word.length();
			}
			return o.count - this.count;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashMap<String, Integer> map = new HashMap<>(); // (단어, 단어개수)
		for (int i = 0; i < N; i++) {
			String word = in.readLine();
			if (word.length() < M) {
				continue;
			}

			// 단어 개수 세기
			if (!map.containsKey(word)) { // map에 해당 단어가 없을 때
				map.put(word, 1);
			} else { // map에 해당 단어가 존재할 때 +1
				int count = map.get(word);
				map.remove(word);
				map.put(word, count + 1);
			}
		}
		
		// (단어, 단어개수)를 우선순위큐에 담기
		// 단어개수 -> 단어 길이 -> 사전순으로 정렬됨
		PriorityQueue<Word> pq = new PriorityQueue<>();
		for(Entry<String, Integer> entry: map.entrySet()) {
			String word = entry.getKey();
			int count = entry.getValue();
			
			Word newWord = new Word(word, count);
			pq.offer(newWord);
		}
		
		// 정답출력
		while(!pq.isEmpty()) {
			sb.append(pq.poll().word).append("\n");
		}
		
		System.out.println(sb);
	}
}
