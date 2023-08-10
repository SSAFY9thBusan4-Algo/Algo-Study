import java.io.*;
import java.util.*;

public class BOJ_1302_S4_베스트셀러 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(in.readLine());
		
		Map<String, Integer> map = new HashMap<>();
		
		while(num-- > 0) {
			String bookTitle = in.readLine();
			map.put(bookTitle, map.getOrDefault(bookTitle, 0) + 1);
		}
		
		String bestSeller = "";
		int sellCount = 0;
		
		for(String book : map.keySet()) {
			if(sellCount < map.get(book)) {
				bestSeller = book;
				sellCount = map.get(book);
			}
			else if(sellCount == map.get(book)) {
				if(book.compareTo(bestSeller) < 0) bestSeller = book;
			}
		}
		
		System.out.println(bestSeller);
		
	}

}
