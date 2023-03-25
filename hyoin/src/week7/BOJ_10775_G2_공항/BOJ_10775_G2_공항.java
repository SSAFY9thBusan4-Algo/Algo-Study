package week7.BOJ_10775_G2_공항;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class BOJ_10775_G2_공항 {

	private static int G;
	private static int P;

	private static int[] parents; // 각 원소의 부모 인덱스를 저장할 배열
	
	// 단위 집합 생성
	private static void makeSet() {
		
		parents = new int[G+1];
		
		// 자신의 부모노드를 자신의 값으로 설정
		for(int i=1; i<=G; i++) {
			parents[i]=i;
		}
	}
	
	// a의 집합 찾기(a의 대표자 찾기)
	private static int findSet(int a) {
		if(parents[a]==a) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
	}
	
	// a, b 두 집합 합치기
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 대표자가 같으면 같은 집합
		if(aRoot==bRoot) {
			return false;
		}
		
		// b집합의 대표자는 a집합 대표자 밑으로 들어감
		parents[bRoot]=aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		G = Integer.parseInt(in.readLine());
		P = Integer.parseInt(in.readLine());

		// 집합 생성
		makeSet();
		
		int result=0;
		for(int i=0; i<P; i++) {
			int gate = Integer.parseInt(in.readLine());
			int docking = findSet(gate);
			
			if(docking==0) { // 더이상 도킹할 수 없을 때
				break;
			}
			
			result++;
			union(docking-1, docking); // 작은 숫자가 큰숫자의 부모가 되도록 union
		}
		
		System.out.println(result);
	}
}