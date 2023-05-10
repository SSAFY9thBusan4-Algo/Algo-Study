pakage src.week14.BOJ_11724_S2_연결요소의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int[] parent; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]);
		int M = Integer.parseInt(in[1]);
		makeSet(N);
		
    // 입력 받으면서 union
		for(int i = 0 ; i < M ; i++) {
			String[] con = br.readLine().split(" ");
			connect(Integer.parseInt(con[0]), Integer.parseInt(con[1]));
		}
		
    // 결과
		int result = count();
		System.out.println(result);
	}
	
  // union 처음 초기화
	private static void makeSet(int N) {
		parent = new int[N+1];
		for(int i = 1 ; i <= N ; i++) parent[i] = i;
	}

  // 
	private static void connect(int i, int j) {		
		int p1 = getParent(i);
		int p2 = getParent(j);
		
		if(p1 != p2) {
			parent[p1] = parent[i] = p2;
		}
	}

	private static int getParent(int n) {
		if(parent[n] == n) return n;
		return parent[n] = getParent(parent[n]);
	}
	
  // 부모가 자신을 가르키는 애만 세기
	private static int count() {
		int cnt = 0;
		for(int i = 1 ; i < parent.length ; i++) {
			if(i == parent[i]) ++cnt;
		}
		return cnt;
	}
}
