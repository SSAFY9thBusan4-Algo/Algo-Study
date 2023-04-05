package week9.BOJ_1043_G4_거짓말;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	static int N, M, parent[];
	static boolean[] truth;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] split = br.readLine().split(" ");
		N = Integer.parseInt(split[0]); // 사람 수
		M = Integer.parseInt(split[1]); // 파티 수

		split = br.readLine().split(" ");
		parent = new int[N+1]; 
		for(int i=1;i<N+1;i++) {
			parent[i] = i;
		}

		truth = new boolean[N + 1]; // 0번 빼고 1~50
		int temp = Integer.parseInt(split[0]);
		if (temp != 0) { // 진실을 아는 사람이 있으면
			for (int i = 0; i < temp; i++) {
				truth[Integer.parseInt(split[i + 1])] = true;
			} // 진실을 알고 있다면
		}
		int[] repre = new int[M];
		for (int i = 0; i < M; i++) {
			split = br.readLine().split(" ");
			temp = Integer.parseInt(split[0]); //해당 파티의 사람 수
			int start = Integer.parseInt(split[1]);
			repre[i] = start; //이 파티의 대표자
			
			if(temp!=1) {
				for (int j = 2; j < temp+1; j++) {
					int peo = Integer.parseInt(split[j]);
					union(peo, start);
				}
			}
		}
		for(int i=1;i<N+1;i++) {
			if(truth[i]) {
				truth[find(i)] = true; //그 대표자를 진실로 바꾼다.
			}
		}

		int result = 0, ple = 0;
		for(int i=0;i<M;i++) {
			ple = find(repre[i]); //이 파티의 대표자의 부모
			if(!truth[ple]) {
				result++;
			}
		}
		System.out.println(result);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		parent[rootB] = rootA;
	}

	private static int find(int a) {
		if(parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}
}
