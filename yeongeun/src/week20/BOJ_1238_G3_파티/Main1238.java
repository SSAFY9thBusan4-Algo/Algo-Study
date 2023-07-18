package src.week20.BOJ_1238_G3_파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1238 {
	
	private static class Node {
		int n, t;
		Node next;
		public Node(int n, int t, Node next) {
			this.n = n;
			this.t = t;
			this.next = next;
		}
	}
	
	private static int N, X;
    private final static int MTIME = 256000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        Node[] linked = new Node[N+1];
        Node[] reverselinked = new Node[N+1];
        for(int i = 0 ; i < M ; i++) {
            // a에서 b까지 c시간이 걸린다.
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            linked[a] = new Node(b, c, linked[a]);
            reverselinked[b] = new Node(a, c, reverselinked[b]);
        }

        // 플로이드 1000^3이라서 안댐.
        // 1. N->X 최소시간 구하기. (X에서 반대로 갔을 때 N에 도착하는 최소 시간.)
        int[] toXTime = getMinTime(reverselinked);
        
        // 2. X->N 최소시간 구하기.
        int[] fromXTime = getMinTime(linked);

        // 3. N->X->N 가장 큰 소요시간 구하기
        int result = 0;
        for(int i = 1 ; i <= N ; i++) {
            int t = toXTime[i] + fromXTime[i];
            if(t > result) result = t;
        }

        System.out.println(result);

    }

	private static int[] getMinTime(Node[] linkedList) {
		int[] minTime = new int[N+1];
		Arrays.fill(minTime, MTIME);
		minTime[X] = 0;
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(X);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int t = minTime[cur];
			
			for(Node node = linkedList[cur] ; node != null ; node = node.next) {
				if(minTime[node.n] > t + node.t) {
					minTime[node.n] = t + node.t;
					queue.offer(node.n);
				}
			}
		}
		
		return minTime;
	}
}