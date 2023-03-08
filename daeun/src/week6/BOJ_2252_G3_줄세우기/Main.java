package week6.BOJ_2252_G3_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Node{
		int num;
		Node link;
		int weight;
		public Node(int num, Node link, int weight) {
			super();
			this.num = num;
			this.link = link;
			this.weight = weight;
		}
	}
	static Node[] adjList;
	static int n, d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int a, b, s;
		for(int t=0;t<T;t++) {
			String[] split = br.readLine().split(" ");
			int n = Integer.parseInt(split[0]); //컴퓨터 개수
			int d = Integer.parseInt(split[1]); //의존성 개수
			int c = Integer.parseInt(split[2]); //해킹당한 컴퓨터 번호 (여기부터 퍼져나간다)
			
			adjList = new Node[n+1];
			for(int i=0;i<d;i++) {
				split = br.readLine().split(" ");
				//a가 b를 의존
				a = Integer.parseInt(split[0]);
				b = Integer.parseInt(split[1]);
				s = Integer.parseInt(split[2]); 
				adjList[b] = new Node(a, adjList[b], s);
			}
			
			int[] path = new int[n+1];
			int[] time = new int[n+1];
			boolean[] visit = new boolean[n+1]; //이미 감염된 곳이라면 패스해야 하니까
			Arrays.fill(time, Integer.MAX_VALUE);
			time[c] = 0;
			Arrays.fill(path, c);
			
			int min, current;
			for(int p=0;p<n;p++) {
				current = -1;
				min = Integer.MAX_VALUE;
				for(int i=1;i<n+1;i++) {
					if(!visit[i] && min>time[i]) {
						min = time[i];
						current = i;
					}
				}
				
				if(current == -1) break;
				
				visit[current] = true;
				
				for(Node temp = adjList[current];temp!=null;temp=temp.link) {
					if(!visit[temp.num] && 
							time[temp.num]>min+temp.weight) {
						time[temp.num] = min+temp.weight;
						path[temp.num] = current;
					}
				}
			}
			
			int cnt = 0, result = 0;
			for(int i=1;i<n+1;i++) {
				if(visit[i]) {
					cnt++;
					result = Math.max(result, time[i]);
				}
			}
			sb.append(cnt+ " "+result).append("\n");
		}
		System.out.println(sb);
	}
	
}