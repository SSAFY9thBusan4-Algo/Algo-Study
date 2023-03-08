package week6.BOJ_10282_G4_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main2 {
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		public Node(int num, int weight) {
			super();
			this.num = num;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	
	static ArrayList<Node>[] adjList;
	static int n, d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int to, from, s;
		for(int t=0;t<T;t++) {
			String[] split = br.readLine().split(" ");
			int n = Integer.parseInt(split[0]); //컴퓨터 개수
			int d = Integer.parseInt(split[1]); //의존성 개수
			int c = Integer.parseInt(split[2]); //해킹당한 컴퓨터 번호 (여기부터 퍼져나간다)
			
			adjList = new ArrayList[n+1];
			for(int i=0;i<n+1;i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i=0;i<d;i++) {
				split = br.readLine().split(" ");
				//a가 b를 의존
				to = Integer.parseInt(split[0]);
				from = Integer.parseInt(split[1]);
				s = Integer.parseInt(split[2]); 
				adjList[from].add(new Node(to, s));
			}
			
			int[] time = new int[n+1];
			Arrays.fill(time, Integer.MAX_VALUE);
			time[c] = 0;
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(c, 0));
			
			Node current;
			int ber, weight;
			while(!pq.isEmpty()) {
				current = pq.poll();
				ber = current.num;
				weight = current.weight;
				
				if(time[ber]<weight) { //더 큰 숫자가 나오면 패스
					continue;
				}
				
				for(Node temp: adjList[ber]) {
					int now = weight + temp.weight;
					if(time[temp.num] > now) { //이미 있는 시간이 더 크면 바꾸기
						time[temp.num] = now;
						pq.offer(new Node(temp.num, now));
					}
				}
			}
			
			int cnt = 0, result = 0;
			for(int i=1;i<n+1;i++) {
				if(time[i]==Integer.MAX_VALUE) { //감염 안 된 컴퓨터는 패스
					continue; 
				}
				cnt++;
				result = Math.max(result, time[i]);
				
			}
			sb.append(cnt+ " "+result).append("\n");
		}
		System.out.println(sb);
	}
	
}