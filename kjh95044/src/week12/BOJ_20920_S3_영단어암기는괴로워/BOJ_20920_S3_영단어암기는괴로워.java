import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    static class Word implements Comparable<Word> {
        String engWord;
        int cnt;

        public Word(String engWord, int cnt) {
            this.engWord = engWord;
            this.cnt = cnt;
        }


        @Override
        public String toString() {
            return "Word{" +
                    "engWord='" + engWord + '\'' +
                    ", cnt=" + cnt +
                    '}';
        }

        @Override
        public int compareTo(Word o) {
            if(this.cnt==o.cnt) {
                if (this.engWord.length() == o.engWord.length()){
                    return this.engWord.compareTo(o.engWord);
                }
                return Integer.compare(o.engWord.length(), this.engWord.length());
            }
            return Integer.compare(o.cnt, this.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> countMap = new HashMap<>();
        List<Word> words = new ArrayList<>();

        for(int i=0; i<N; i++) {
            String input = in.readLine();
            if(input.length()>= M) {
                countMap.put(input, countMap.getOrDefault(input, 0)+1);
            }
        }


        countMap.forEach(
                (k,v) -> {
                    Word word = new Word(k,v);
                    words.add(word);
        }
        );
        Collections.sort(words);

        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i).engWord).append("\n");
        }
        System.out.println(sb);
    }
}
