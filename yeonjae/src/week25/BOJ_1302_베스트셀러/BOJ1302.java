package week25;

import java.io.*;
import java.util.*;

public class BOJ1302 {

    static int N;
    static Map<String, Integer> books = new HashMap<>();
    static String s;

    static class BookAndCount implements Comparable<BookAndCount> {
        String book;
        int count;

        public BookAndCount(String book, int count) {
            this.book = book;
            this.count = count;
        }

        @Override
        public int compareTo(BookAndCount o) {
            if(this.count != o.count) {
                return Integer.compare(o.count, this.count);
            }
            return String.CASE_INSENSITIVE_ORDER.compare(this.book, o.book);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        for(int i = 0; i < N; i++) {
            s = in.readLine();
            if(books.containsKey(s)) {
                books.put(s, books.get(s) + 1);
            }
            else {
                books.put(s, 1);
            }
        }

        BookAndCount[] bookAndCounts = new BookAndCount[books.size()];
        int idx = 0;
        for(String book : books.keySet()) {
            bookAndCounts[idx++] = new BookAndCount(book, books.get(book));
        }

        Arrays.sort(bookAndCounts);
        System.out.println(bookAndCounts[0].book);
    }
}
