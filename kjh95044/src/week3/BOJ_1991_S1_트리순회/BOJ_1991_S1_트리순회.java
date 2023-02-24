package week3.BOJ_1991_S1_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_1991_S1_트리순회 {
	
	static StringBuilder sb = new StringBuilder();
	static HashMap<Character, Character[]>map = new HashMap<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String[] split = br.readLine().split(" ");
			char node = split[0].charAt(0);
			map.put(node, new Character[] {split[1].charAt(0), split[2].charAt(0)});
		}
		
		dfsPreorder('A');
		sb.append("\n");
		dfsInorder('A');
		sb.append("\n");
		dfsPostOrder('A');
		sb.append("\n");
		System.out.println(sb);
	}


	// 전위 순회
	private static void dfsPreorder(char node) {
		sb.append(node);
		for(char child : map.get(node)) {
			if(child == '.') {
				continue;
			}
			dfsPreorder(child);
		}
	}
	
	// 중위 순회
	private static void dfsInorder(char node) {
		if(map.get(node)[0] != '.') {
			dfsInorder(map.get(node)[0]);
		}
		sb.append(node);
		if(map.get(node)[1] != '.') {
			dfsInorder(map.get(node)[1]);
		}	
	}
	
	//후위 순회
	private static void dfsPostOrder(char node) {
		for(char child : map.get(node)) {
			if(child == '.') {
				continue;
			}
			dfsPostOrder(child);
		}
		sb.append(node);
	}
}