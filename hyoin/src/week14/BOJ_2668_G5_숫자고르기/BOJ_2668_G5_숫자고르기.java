package week14.BOJ_2668_G5_숫자고르기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2668_G5_숫자고르기 {
	
	private static int N;
	private static int[] input;
	private static boolean[] isVisited;
	private static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		input = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine());
			input[i]=Integer.parseInt(st.nextToken());
		}
		
		// 해당 숫자가 사이클을 이루는지 확인
		isVisited = new boolean[N+1];
		pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			isVisited[i]=true;
			dfs(i,i);
			isVisited[i]=false;
		}
		
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) {
			sb.append(pq.poll()).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int start, int curNum) { // start : 초기 시작 값, curNum : 현재 숫자
		if(!isVisited[input[curNum]]) { // 해당 숫자에 방문한 적이 없을 때
			isVisited[input[curNum]]=true;
			dfs(start, input[curNum]);
			isVisited[input[curNum]]=false;
		}
		
		if(input[curNum]==start) { // 연결된 숫자가 처음에 시작한 숫자와 같을 때 사이클
			pq.offer(start);
		}
		
	}
}
